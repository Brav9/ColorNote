package com.khalbro.colornote.presentation.allnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.usecase.DeleteNoteUseCase
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    application: Application,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    getAllNotesUseCase: GetAllNotesUseCase
) : AndroidViewModel(application) {

    private val _sortType: MutableLiveData<SortType> = MutableLiveData(SortType.SORT_DATE)
    private val _sortDirection: MutableLiveData<SortDirection> =
        MutableLiveData(SortDirection.DESCENDING_SORT)
    private val _allNotes: LiveData<List<InfoNote>> = getAllNotesUseCase.invoke()
    var allNotes: LiveData<List<InfoNote>> = _allNotes.switchMap { notes ->
        _sortType.switchMap { sortType ->
            _sortDirection.map { sortDirection ->
                getSortedNotes(notes, sortType, sortDirection)
            }
        }
    }

    fun deleteNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        deleteNoteUseCase.invoke(id)
    }

    private fun getSortedNotes(
        notes: List<InfoNote>,
        sortType: SortType,
        sortDirection: SortDirection
    ): List<InfoNote> {
        return when (sortType) {
            SortType.SORT_DATE -> {
                when (sortDirection) {
                    SortDirection.DESCENDING_SORT -> notes.sortedByDescending { it.date }
                    SortDirection.ASCENDING_SORT -> notes.sortedBy { it.date }
                }
            }

            SortType.SORT_TITLE -> {
                when (sortDirection) {
                    SortDirection.DESCENDING_SORT -> notes.sortedByDescending { it.title }
                    SortDirection.ASCENDING_SORT -> notes.sortedBy { it.title }
                }
            }
        }
    }

    fun onSortChange(sortType: SortType) {
        _sortType.value = sortType
    }

    fun onSortDirectionChange(sortDirection: SortDirection) {
        _sortDirection.value = sortDirection
    }
}

