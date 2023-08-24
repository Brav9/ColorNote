package com.khalbro.colornote.presentation.allnotes

import android.app.AlertDialog
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.khalbro.colornote.R
import com.khalbro.colornote.databinding.FragmentNotesBinding
import com.khalbro.colornote.domain.models.InfoNote
import org.koin.androidx.viewmodel.ext.android.viewModel

enum class SortType {
    SORT_DATE, SORT_TITLE
}

enum class SortDirection {
    ASCENDING_SORT, DESCENDING_SORT
}

class NotesFragment : Fragment(), NotesAdapter.OnClickListener {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModel<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("Ololo", "onCreateView: $inflater")
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        var isClicked: Boolean = true

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuSortingByDate -> {
                        noteViewModel.onSortChange(SortType.SORT_DATE)
                        true
                    }

                    R.id.menuSortingByTitle -> {
                        noteViewModel.onSortChange(SortType.SORT_TITLE)
                        true
                    }

                    R.id.menuDirectionOfSorting -> {
                        isClicked = if (isClicked) {
                            noteViewModel.onSortDirectionChange(SortDirection.DESCENDING_SORT)
                            menuItem.setIcon(R.drawable.baseline_arrow_downward_24)
                            false
                        } else {
                            noteViewModel.onSortDirectionChange(SortDirection.ASCENDING_SORT)
                            menuItem.setIcon(R.drawable.baseline_arrow_upward_24)
                            true
                        }
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        Log.d("Ololo", "onViewCreated: 01")

        val adapter = NotesAdapter(this)

        binding.rvNotesFragment.adapter = adapter
        binding.rvNotesFragment.setHasFixedSize(true)
        if (getOrientationLandScape()) {
            binding.rvNotesFragment.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        noteViewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.fabNewNote.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_notesFragment_to_editNoteFragment)
            Log.d("Ololo", "fabNewNote: $view")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onItemClick(infoNote: InfoNote) {
        infoNote.id?.let {
            val view: NotesFragment = this@NotesFragment
            val action =
                NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(infoNote.id)
            view.findNavController().navigate(action)
        }
    }

    override fun onLongItemClick(infoNote: InfoNote) {
        deleteItem(infoNote)
    }

    private fun getOrientationLandScape(): Boolean {
        return when (resources.configuration.orientation) {
            ORIENTATION_LANDSCAPE -> true
            else -> false
        }
    }

    private fun deleteItem(infoNote: InfoNote) {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.run {
            setTitle("Delete")
            setMessage("Do you want to delete this item?")
            setPositiveButton("Yes") { _, _ ->
                infoNote.id?.let { noteViewModel.deleteNoteById(it) }
            }
            setNegativeButton("No") { _, _ -> }
            val alert = alertBuilder.create()
            alert.show()
        }
    }
}

