package com.khalbro.colornote.presentation.editnote

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.khalbro.colornote.R
import com.khalbro.colornote.databinding.FragmentEditNoteBinding

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
        val menuHost: MenuHost = requireActivity()
        val argsId: EditNoteFragmentArgs by navArgs()
        val id = argsId.noteId

        fragmentNotesBinding = binding
        editNoteViewModel = ViewModelProvider(this)[EditNoteViewModel::class.java]
        editNoteViewModel.getNoteById(id).toString()

        editNoteViewModel.note.observe(this.viewLifecycleOwner) {
            if (it.text != binding.etText.text.toString()) {
                binding.etText.setText(it.text)
            }
            val color = Color.parseColor(it.getBackgroundColor())
            binding.constraintLayoutEditNote.setBackgroundColor(color)
            val colorSecond = Color.parseColor(it.getBackgroundColorVerticalLine())
            binding.ivVerticalLine.setBackgroundColor(colorSecond)
            binding.btnSaveNote.setBackgroundColor(colorSecond)
        }

        editNoteViewModel.note.observe(this.viewLifecycleOwner) {
            if (it.text != binding.etTitle.text.toString()) {
                binding.etTitle.setText(it.title)
            }
//            val color = Color.parseColor(it.getBackgroundColor())
//            binding.constraintLayoutEditNote.setBackgroundColor(color)
        }

        editNoteViewModel.navigateBackEvent.observe(this.viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding.etText.doOnTextChanged { text, start, before, count ->
            text?.let {
                editNoteViewModel.onTextChanged(it.toString())
            }
        }

        binding.etTitle.doOnTextChanged { text, start, before, count ->
            text?.let {
                editNoteViewModel.onTitleChanged(it.toString())
            }
        }

        binding.btnSaveNote.setOnClickListener {
            editNoteViewModel.onSaveClick()
        }

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_edit_note, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuNude -> {
                        binding.constraintLayoutEditNote.setBackgroundResource(R.color.nude)
                        true
                    }
                    R.menu.menu_edit_note -> true
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
