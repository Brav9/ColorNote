package com.khalbro.colornote.presentation.allnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import com.khalbro.colornote.domain.usecase.DeleteNoteUseCase
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

//    private val _deleteNoteById: MutableLiveData<InfoNote> = MutableLiveData<InfoNote>()
////    var deleteNoteById: LiveData<InfoNote> = _deleteNoteById

    @Deprecated("Use Koin")
    private val repository: InfoNoteRepository =
        InfoNoteRepositoryImpl(notesDao = NotesRoomDatabase.getDatabase(application).notesDao())

    private val deleteNoteUseCase by lazy(LazyThreadSafetyMode.NONE ) {
        DeleteNoteUseCase(infoNoteRepository = repository)
    }

    private val getAllNotesUseCase = GetAllNotesUseCase(infoNoteRepository = repository)
    val allNotes = getAllNotesUseCase.invoke()

    fun deleteNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO ) {
        deleteNoteUseCase.invoke(id)

    }
}
