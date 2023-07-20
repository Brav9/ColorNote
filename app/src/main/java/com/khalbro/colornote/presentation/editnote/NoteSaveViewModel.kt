package com.khalbro.colornote.presentation.editnote

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import com.khalbro.colornote.domain.usecase.SaveNoteUseCase
import com.khalbro.colornote.presentation.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteSaveViewModel(application: Application) : AndroidViewModel(application) {

    @Deprecated("Use Coin")
    private val repository: InfoNoteRepository =
        InfoNoteRepositoryImpl(notesDao = NotesRoomDatabase.getDatabase(application).notesDao())

    private val saveNoteUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveNoteUseCase(infoNoteRepository = repository)
    }

    fun insertNote(note: InfoNote) = viewModelScope.launch(Dispatchers.IO) {
        saveNoteUseCase.invoke(note)
    }
}