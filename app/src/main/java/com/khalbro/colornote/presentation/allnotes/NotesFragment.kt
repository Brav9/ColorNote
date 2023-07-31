package com.khalbro.colornote.presentation.allnotes

import android.app.AlertDialog
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout.TabGravity
import com.khalbro.colornote.R
import com.khalbro.colornote.databinding.FragmentNotesBinding
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.presentation.editnote.EditNoteFragment

class NotesFragment : Fragment(), NotesAdapter.OnClickListener {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesViewModel: NotesViewModel

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
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        Log.d("Ololo", "onViewCreated: 01")

        val adapter = NotesAdapter(this)
        binding.rvNotesFragment.adapter = adapter
        notesViewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
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
                infoNote.id?.let { notesViewModel.deleteNoteById(it) }
            }
            setNegativeButton("No") { _, _ ->

            }
            val alert = alertBuilder.create()
            alert.show()
        }
    }
}

