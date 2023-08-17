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

    val allNotes = getAllNotesUseCase.invoke()

    fun deleteNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        deleteNoteUseCase.invoke(id)
    }
    fun sortingNotesByDate(){
        allNotes
    }

    fun sortingNotesByTitle(){}
}
