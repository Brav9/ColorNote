package com.khalbro.colornote.presentation.editnote

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.khalbro.colornote.R
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.databinding.FragmentEditNoteBinding
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.presentation.allnotes.NotesFragment
import kotlinx.coroutines.job
import kotlin.random.Random

class EditNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    private lateinit var noteSaveViewModel: NoteSaveViewModel
    private var fragmentNotesBinding: FragmentEditNoteBinding? = null
    private val note: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditNoteBinding.bind(view)
        fragmentNotesBinding = binding

        noteSaveViewModel = ViewModelProvider(this)[NoteSaveViewModel::class.java]

        binding.etTitle.setText(note?.title)
        binding.etText.setText(note?.text)

        val argsId: EditNoteFragmentArgs by navArgs()
        val id = argsId.noteId

        noteSaveViewModel.resultNoteById.observe(this.viewLifecycleOwner) {
            binding.etText.setText(it.text)
            binding.etTitle.setText(it.title)
        }
        noteSaveViewModel.getNoteById(id).toString()

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val text = binding.etText.text.toString()
            if (text.isBlank() and text.isBlank()) {

                Toast.makeText(activity, "Title and Note is Empty", Toast.LENGTH_SHORT).show()
            } else {
                val mNote = InfoNote(text = text, title = title, id = Random.nextLong())
                if (note == null) {
                    noteSaveViewModel.insertNote(mNote)
                    findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
                }
            }
        }
    }
}
