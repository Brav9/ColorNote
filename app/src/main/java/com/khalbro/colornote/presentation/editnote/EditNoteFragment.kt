package com.khalbro.colornote.presentation.editnote

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.khalbro.colornote.R
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.databinding.FragmentEditNoteBinding
import com.khalbro.colornote.domain.models.InfoNote
import kotlin.random.Random

class EditNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    private lateinit var editNoteViewModel: EditNoteViewModel
    private var fragmentNotesBinding: FragmentEditNoteBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditNoteBinding.bind(view)
        fragmentNotesBinding = binding

        editNoteViewModel = ViewModelProvider(this)[EditNoteViewModel::class.java]

//        binding.etTitle.setText(note?.title)
//        binding.etText.setText(note?.text)
//        binding.constraintLayoutEditNote.setBackgroundResource(R.color.ivory)
//        context?.let { ContextCompat.getColor(it, R.color.nude) }
//            ?.let { binding.constraintLayoutEditNote.setBackgroundColor(it) }

//        val color = Color.parseColor("#EDE9E3")
//        binding.constraintLayoutEditNote.setBackgroundColor(color)

        val argsId: EditNoteFragmentArgs by navArgs()
        val id = argsId.noteId

        editNoteViewModel.getNoteById(id).toString()

        editNoteViewModel.note.observe(this.viewLifecycleOwner) {
            if (it.text != binding.etText.text.toString()) {
                binding.etText.setText(it.text)
            }
            val color = Color.parseColor(it.getBackgroundColor())
            binding.constraintLayoutEditNote.setBackgroundColor(color)
        }

        editNoteViewModel.note.observe(this.viewLifecycleOwner) {
            if (it.text != binding.etTitle.text.toString()) {
                binding.etTitle.setText(it.title)
            }
            val color = Color.parseColor(it.getBackgroundColor())
            binding.constraintLayoutEditNote.setBackgroundColor(color)
        }


        editNoteViewModel.navigateBackEvent.observe(this.viewLifecycleOwner) {
//            findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
            findNavController().popBackStack()
        }


        binding.etText.doOnTextChanged { text, start, before, count ->
            text?.let {
                editNoteViewModel.onTextChanged(it.toString())
            }
//            if (text != null) {
//                noteSaveViewModel.onTextChanged(text.toString())
//            }
        }

        binding.etTitle.doOnTextChanged { text, start, before, count ->
            text?.let {
                editNoteViewModel.onTitleChanged(it.toString())
            }
        }

        binding.btnSaveNote.setOnClickListener {
            editNoteViewModel.onSaveClick()
//            val title = binding.etTitle.text.toString()
//            val text = binding.etText.text.toString()
//            if (text.isBlank() and text.isBlank()) {
//
//                Toast.makeText(activity, "Title and Note is Empty", Toast.LENGTH_SHORT).show()
//            } else {
//                val mNote = InfoNote(
//                    text = text,
//                    title = title,
//                    id = Random.nextLong(),
//                    color = ""
//                )
//                if (note == null) {
//                    editNoteViewModel.insertNote(mNote)
//                    findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
//                }
//            }
        }
    }
}
