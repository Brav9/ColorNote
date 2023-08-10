package com.khalbro.colornote.presentation.editnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.usecase.GetNoteByIdUseCase
import com.khalbro.colornote.domain.usecase.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditNoteViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : AndroidViewModel(application) {

    private val argsId = EditNoteFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val id = argsId.noteId
    private val _note: MutableLiveData<InfoNote> = MutableLiveData<InfoNote>()
    var note: LiveData<InfoNote> = _note

    private val _navigateBackEvent: MutableLiveData<Unit> = MutableLiveData<Unit>()
    var navigateBackEvent: LiveData<Unit> = _navigateBackEvent

    init {
        getNoteById(id)
    }

    private fun insertNote(note: InfoNote) = viewModelScope.launch(Dispatchers.IO) {
        saveNoteUseCase.invoke(note)
    }

    private fun getNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        val noteById = getNoteByIdUseCase.invoke(id)
        withContext(Dispatchers.Main) {
            noteById?.let {
                _note.value = it
            } ?: run {
                _note.value =
                    InfoNote(text = "", title = "", id = null, date = InfoNote.getDateNote())
            }
        }
    }

    fun onTextChanged(text: String) {
        val currentNote = note.value
        currentNote?.let {
            if (currentNote.text != text) {
                _note.value = currentNote.copy(text = text)
            }
        }
    }

    fun onTitleChanged(text: String) {
        val currentNote = note.value
        currentNote?.let {
            if (currentNote.title != text) {
                _note.value = currentNote.copy(title = text)
            }
        }
    }

    fun onSaveClick() {
        val currentNote = note.value
        currentNote?.let {
            insertNote(it.copy(date = InfoNote.getDateNote()))
            _navigateBackEvent.value = Unit
        }
    }

    fun onColorChanged(color: String) {
        val currentNote = note.value
        currentNote?.let {
            if (currentNote.color != color) {
                _note.value = currentNote.copy(color = color)
            }
        }
    }
}