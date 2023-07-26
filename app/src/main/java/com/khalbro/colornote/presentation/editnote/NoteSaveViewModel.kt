package com.khalbro.colornote.presentation.editnote

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import com.khalbro.colornote.domain.usecase.GetNoteByIdUseCase
import com.khalbro.colornote.domain.usecase.SaveNoteUseCase
import com.khalbro.colornote.presentation.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteSaveViewModel(application: Application) : AndroidViewModel(application) {

    private val _resultNoteById: MutableLiveData<InfoNote> = MutableLiveData<InfoNote>()
    var resultNoteById: LiveData<InfoNote> = _resultNoteById

    @Deprecated("Use Coin")
    private val repository: InfoNoteRepository =
        InfoNoteRepositoryImpl(notesDao = NotesRoomDatabase.getDatabase(application).notesDao())

    private val saveNoteUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveNoteUseCase(infoNoteRepository = repository)
    }

    private val getNoteByIdUseCase = GetNoteByIdUseCase(infoNoteRepository = repository)

    fun insertNote(note: InfoNote) = viewModelScope.launch(Dispatchers.IO) {
        saveNoteUseCase.invoke(note)
    }

    fun getNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        val noteById = getNoteByIdUseCase.invoke(id)
        withContext(Dispatchers.Main) {
            noteById?.let {
                _resultNoteById.value = it
            } ?: run {
                _resultNoteById.value = InfoNote(text = "", title = "", id = null)
            }
        }
    }
}