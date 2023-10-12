package com.khalbro.colornote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.domain.models.SortType
import com.khalbro.colornote.domain.usecase.ChangeSortDirectionNotesUseCase
import com.khalbro.colornote.domain.usecase.ChangeSortTypeNotesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
//    private val isSortTableEmptyUseCase: IsSortTableEmptyUseCase,
    private val changeSortTypeNotesUseCase: ChangeSortTypeNotesUseCase,
    private val changeSortDirectionNotesUseCase: ChangeSortDirectionNotesUseCase
) : ViewModel() {

//    init {
////        isSortTableEmpty()
//    }
//
//    private fun isSortTableEmpty(): Boolean {
//        if (isSortTableEmpty()) {
//            viewModelScope.launch(Dispatchers.IO) {
//                isSortTableEmptyUseCase.invoke()
//            }
//        } else {
//        }
//        return true
//    }

    fun changeSortTypeNotes(sortTypeNotes: SortType) =
        viewModelScope.launch(Dispatchers.IO) {
            changeSortTypeNotesUseCase.invoke(sortTypeNotes)
        }

    fun changeSortDirectionNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            changeSortDirectionNotesUseCase.invoke()
        }
    }
}