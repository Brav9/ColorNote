package com.khalbro.colornote.presentation.editnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.khalbro.colornote.R
import com.khalbro.colornote.databinding.FragmentEditNoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteFragment : Fragment() {

    private val editNoteViewModel by viewModel<EditNoteViewModel>()
    private var fragmentNotesBinding: FragmentEditNoteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditNoteBinding.bind(view)
        val menuHost: MenuHost = requireActivity()
        fragmentNotesBinding = binding

        editNoteViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is InfoNoteState.Loading -> {
                    binding.materialCardView.visibility = View.INVISIBLE
                    binding.btnSaveNote.visibility = View.INVISIBLE
                }

                is InfoNoteState.Success -> {
                    binding.materialCardView.visibility = View.VISIBLE
                    binding.btnSaveNote.visibility = View.VISIBLE

                    if (state.infoNote.text != binding.etText.text.toString()) {
                        binding.etText.setText(state.infoNote.text)
                    }
                    if (state.infoNote.title != binding.etTitle.text.toString()) {
                        binding.etTitle.setText(state.infoNote.title)
                    }
                    val color = state.infoNote.getBackgroundColor(binding.root.context)
                    binding.constraintLayoutEditNote.setBackgroundColor(color)
                    val colorSecond =
                        state.infoNote.getBackgroundColorVerticalLine(binding.root.context)
                    binding.ivVerticalLine.setBackgroundColor(colorSecond)
                    binding.btnSaveNote.setBackgroundColor(colorSecond)
                }
            }
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
                    R.id.menuGrayishOrange -> {
                        binding.constraintLayoutEditNote.setBackgroundResource(R.color.grayish_orange)
                        binding.ivVerticalLine.setBackgroundResource(R.color.grayish_orange_line)
                        val color = ContextCompat.getColor(
                            binding.root.context, R.color.grayish_orange_line
                        )
                        binding.btnSaveNote.setBackgroundColor(color)
                        editNoteViewModel.onColorChanged("2")
                        true
                    }

                    R.id.menuBlue -> {
                        binding.constraintLayoutEditNote.setBackgroundResource(R.color.blue)
                        binding.ivVerticalLine.setBackgroundResource(R.color.blue_line)
                        context?.let {
                            ContextCompat.getColor(
                                it, R.color.blue_line
                            )
                        }?.let {
                            binding.btnSaveNote.setBackgroundColor(it)
                        }
                        editNoteViewModel.onColorChanged("5")
                        true
                    }

                    R.id.menuGray -> {
                        binding.constraintLayoutEditNote.setBackgroundResource(R.color.gray)
                        binding.ivVerticalLine.setBackgroundResource(R.color.gray_line)
                        context?.let {
                            ContextCompat.getColor(
                                it, R.color.gray_line
                            )
                        }?.let {
                            binding.btnSaveNote.setBackgroundColor(it)
                        }
                        editNoteViewModel.onColorChanged("1")
                        true
                    }

                    R.id.menuRed -> {
                        binding.constraintLayoutEditNote.setBackgroundResource(R.color.red)
                        binding.ivVerticalLine.setBackgroundResource(R.color.red_line)
                        context?.let {
                            ContextCompat.getColor(
                                it, R.color.red_line
                            )
                        }?.let {
                            binding.btnSaveNote.setBackgroundColor(it)
                        }
                        editNoteViewModel.onColorChanged("4")
                        true
                    }

                    R.id.menuGreen -> {
                        binding.constraintLayoutEditNote.setBackgroundResource(R.color.green)
                        binding.ivVerticalLine.setBackgroundResource(R.color.green_line)
                        context?.let {
                            ContextCompat.getColor(
                                it, R.color.green_line
                            )
                        }?.let {
                            binding.btnSaveNote.setBackgroundColor(it)
                        }
                        editNoteViewModel.onColorChanged("3")
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentNotesBinding = null
    }
}
