package com.khalbro.colornote.presentation.editnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.khalbro.colornote.R
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.databinding.FragmentEditNoteBinding
import com.khalbro.colornote.domain.models.InfoNote
import kotlin.random.Random

class EditNoteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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


        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val text = binding.etText.text.toString()
            if (text.isBlank() and text.isBlank()) {

                Toast.makeText(activity, "Title and Note is Empty", Toast.LENGTH_SHORT).show()
            } else {
                val mNote = InfoNote(textNote = text, noteTitle = title, id = Random.nextLong())
                if (note == null) {
                    noteSaveViewModel.insertNote(mNote)
                    findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
                }
            }
        }
    }
}
