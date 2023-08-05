package com.khalbro.colornote.presentation.allnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.domain.usecase.DeleteNoteUseCase
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    application: Application,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    getAllNotesUseCase: GetAllNotesUseCase
) : AndroidViewModel(application) {

//    @Deprecated("Use Koin")
//    private val repository: InfoNoteRepository =
//        InfoNoteRepositoryImpl(notesDao = NotesRoomDatabase.getDatabase(application).notesDao())

//    private val deleteNoteUseCase by lazy(LazyThreadSafetyMode.NONE) {
//        DeleteNoteUseCase(infoNoteRepository = repository)
//    }

//    private val getAllNotesUseCase = GetAllNotesUseCase(infoNoteRepository = repository)
//    val allNotes = getAllNotesUseCase.invoke()

    val allNotes = getAllNotesUseCase.invoke()

    fun deleteNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        deleteNoteUseCase.invoke(id)
    }
}
