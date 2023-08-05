package com.khalbro.colornote.presentation.allnotes

import android.app.AlertDialog
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
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.khalbro.colornote.R
import com.khalbro.colornote.databinding.FragmentNotesBinding
import com.khalbro.colornote.domain.models.InfoNote
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment(), NotesAdapter.OnClickListener {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModel<NotesViewModel>()
//    private lateinit var notesViewModel: NotesViewModel

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
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.menu.menu_main -> {
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

//        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        Log.d("Ololo", "onViewCreated: 01")

        val adapter = NotesAdapter(this)
        binding.rvNotesFragment.adapter = adapter
        noteViewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            Log.d("Ololo", "observe: $notes")
            adapter.submitList(notes)
        })

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

