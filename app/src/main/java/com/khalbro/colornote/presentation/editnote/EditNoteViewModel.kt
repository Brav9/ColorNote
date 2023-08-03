package com.khalbro.colornote.presentation.editnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import com.khalbro.colornote.domain.usecase.GetNoteByIdUseCase
import com.khalbro.colornote.domain.usecase.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {

    private val _note: MutableLiveData<InfoNote> = MutableLiveData<InfoNote>()
    var note: LiveData<InfoNote> = _note

    private val _navigateBackEvent: MutableLiveData<Unit> = MutableLiveData<Unit>()
    var navigateBackEvent: LiveData<Unit> = _navigateBackEvent

    @Deprecated("Use Koin")
    private val repository: InfoNoteRepository =
        InfoNoteRepositoryImpl(notesDao = NotesRoomDatabase.getDatabase(application).notesDao())

    private val saveNoteUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveNoteUseCase(infoNoteRepository = repository)
    }

    private val getNoteByIdUseCase = GetNoteByIdUseCase(infoNoteRepository = repository)

    private fun insertNote(note: InfoNote) = viewModelScope.launch(Dispatchers.IO) {
        saveNoteUseCase.invoke(note)
    }

    fun getNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        val noteById = getNoteByIdUseCase.invoke(id)
        withContext(Dispatchers.Main) {
            noteById?.let {
                _note.value = it
            } ?: run {
                _note.value = InfoNote(text = "", title = "", id = null)
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
            insertNote(it)
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