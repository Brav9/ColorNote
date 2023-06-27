package com.khalbro.colornote.presentation.allnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.domain.models.InfoNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InfoNoteRepositoryImpl
    val allNotes: LiveData<List<Note>>

    init {

        val notesDao = NotesRoomDatabase.getDatabase(application, viewModelScope).notesDao()
        repository = InfoNoteRepositoryImpl(notesDao)
        allNotes = repository.getAllNotesInfoNote()
    }

    fun saveNote(note: InfoNote) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveInfoNote(note)
    }

    fun deleteNote(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteInfoNote(id)
    }


}