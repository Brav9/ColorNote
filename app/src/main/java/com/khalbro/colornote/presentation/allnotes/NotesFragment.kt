package com.khalbro.colornote.presentation.allnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.khalbro.colornote.R
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.databinding.FragmentNotesBinding
import com.khalbro.colornote.domain.usecase.DeleteNoteUseCase
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import com.khalbro.colornote.domain.usecase.GetNoteByIdUseCase

class NotesFragment : Fragment() {

    private lateinit var infoNoteRepository: InfoNoteRepositoryImpl
    private val getAllNotesUseCase by lazy { GetAllNotesUseCase(infoNoteRepository = infoNoteRepository) }
    private val getNoteByIdUseCase by lazy { GetNoteByIdUseCase(infoNoteRepository = infoNoteRepository) }
    private val deleteNoteUseCase by lazy { DeleteNoteUseCase(infoNoteRepository = infoNoteRepository) }
    private var _binding: FragmentNotesBinding? = null
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNotesBinding.bind(view)
        _binding = binding
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val adapter = activity?.applicationContext?.let { NotesAdapter() }
        binding.rvNotesFragment.adapter = adapter
        binding.rvNotesFragment.setHasFixedSize(true)
        binding.rvNotesFragment.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        notesViewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            adapter?.submitList(notes)
        }

        binding.fabNewNote.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_notesFragment_to_editNoteFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}