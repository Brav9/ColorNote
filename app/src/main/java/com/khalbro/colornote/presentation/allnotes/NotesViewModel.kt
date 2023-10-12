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
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import com.khalbro.colornote.domain.usecase.GetSelectedSortDirectionUseCase
import com.khalbro.colornote.domain.usecase.GetSortedNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val deleteNoteUseCase: DeleteNoteUseCase,
    getAllNotesUseCase: GetAllNotesUseCase,
    private val changeSortTypeNotesUseCase: ChangeSortTypeNotesUseCase,
    private val changeSortDirectionUseCase: ChangeSortDirectionNotesUseCase,
    private val getSelectedSortDirectionUseCase: GetSelectedSortDirectionUseCase,
    val getSortedNotesUseCase: GetSortedNotesUseCase
) : ViewModel() {

//    private val _state: MutableLiveData<InfoNoteState> = MutableLiveData<InfoNoteState>()
//    var state: LiveData<InfoNoteState> = _state

//    private val _sortNotes: LiveData<InfoSortNotes> = getSortedNotesUseCase.invoke()
//    private val _allNotes: LiveData<List<InfoNote>> = getAllNotesUseCase.invoke()
//    var allNotes: LiveData<List<InfoNote>> = _allNotes.switchMap { notes ->
//        _sortNotes.map { sortNotes ->
//            getSortedNotes(notes, sortNotes)
//        }
//    }


    //    private val _sortType: MutableLiveData<SortType> = MutableLiveData(SortType.SORT_DATE)
//    private val _sortDirection: MutableLiveData<SortDirection> =
//        MutableLiveData(SortDirection.DESCENDING_SORT)
    val allNotes: LiveData<List<InfoNote>> = getSortedNotesUseCase.invoke()
    val sortDirection: LiveData<SortDirection> = getSelectedSortDirectionUseCase.invoke()

    fun deleteNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        deleteNoteUseCase.invoke(id)
    }

//    private fun getSortedNotes(
//        notes: List<InfoNote>,
//        sortNotes: InfoSortNotes
//    ): List<InfoNote> {
//        return when (sortNotes.sortType) {
//            SortType.SORT_DATE -> {
//                when (sortNotes.sortDirection) {
//                    SortDirection.DESCENDING_SORT -> notes.sortedByDescending { it.date }
//                    SortDirection.ASCENDING_SORT -> notes.sortedBy { it.date }
//                }
//            }
//
//            SortType.SORT_TITLE -> {
//                when (sortNotes.sortDirection) {
//                    SortDirection.DESCENDING_SORT -> notes.sortedByDescending { it.title }
//                    SortDirection.ASCENDING_SORT -> notes.sortedBy { it.title }
//                }
//            }
//        }
//    }

//    private fun getSortedNotes(
//        notes: List<InfoNote>,
//        sortType: SortType,
//        sortDirection: SortDirection
//    ): List<InfoNote> {
//        return when (sortType) {
//            SortType.SORT_DATE -> {
//                when (sortDirection) {
//                    SortDirection.DESCENDING_SORT -> notes.sortedByDescending { it.date }
//                    SortDirection.ASCENDING_SORT -> notes.sortedBy { it.date }
//                }
//            }
//
//            SortType.SORT_TITLE -> {
//                when (sortDirection) {
//                    SortDirection.DESCENDING_SORT -> notes.sortedByDescending { it.title }
//                    SortDirection.ASCENDING_SORT -> notes.sortedBy { it.title }
//                }
//            }
//        }
//    }


//    fun onSortChange(sortType: SortType) {
//        _sortType.value = sortType
//    }
//
//    fun onSortDirectionChange(sortDirection: SortDirection) {
//        _sortDirection.value = sortDirection
//    }

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

