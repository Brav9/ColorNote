package com.khalbro.colornote.presentation.editnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.domain.models.InfoNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteSaveViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InfoNoteRepositoryImpl

    init {
        val notesDao = NotesRoomDatabase.getDatabase(application, viewModelScope).notesDao()
        repository = InfoNoteRepositoryImpl(notesDao)
    }

    fun insertNote(note: InfoNote) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveInfoNote(note)
    }
}