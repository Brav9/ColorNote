package com.khalbro.colornote.presentation.allnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.domain.models.SortType
import com.khalbro.colornote.domain.usecase.ChangeSortDirectionNotesUseCase
import com.khalbro.colornote.domain.usecase.ChangeSortTypeNotesUseCase
import com.khalbro.colornote.domain.usecase.DeleteNoteUseCase
import com.khalbro.colornote.domain.usecase.GetSelectedSortDirectionUseCase
import com.khalbro.colornote.domain.usecase.GetSortedNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val changeSortTypeNotesUseCase: ChangeSortTypeNotesUseCase,
    private val changeSortDirectionUseCase: ChangeSortDirectionNotesUseCase,
    getSelectedSortDirectionUseCase: GetSelectedSortDirectionUseCase,
    val getSortedNotesUseCase: GetSortedNotesUseCase
) : ViewModel() {

    val allNotes: LiveData<List<InfoNote>> = getSortedNotesUseCase.invoke()
    val sortDirection: LiveData<SortDirection> = getSelectedSortDirectionUseCase.invoke()

    fun deleteNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        deleteNoteUseCase.invoke(id)
    }

    fun changeSortTypeNotes(sortTypeNotes: SortType) =
        viewModelScope.launch(Dispatchers.IO) {
            changeSortTypeNotesUseCase.invoke(sortTypeNotes)
        }

    fun changeSortDirectionNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            changeSortDirectionUseCase.invoke()
        }
    }
}

