package com.khalbro.colornote.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.domain.models.SortType

class GetSortedNotesUseCase(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getSelectedSortTypeUseCase: GetSelectedSortTypeUseCase,
    private val getSelectedSortDirectionUseCase: GetSelectedSortDirectionUseCase
) {

    operator fun invoke(): LiveData<List<InfoNote>> {
        return getAllNotesUseCase.invoke().switchMap { notes ->
            getSelectedSortTypeUseCase.invoke().switchMap { sortType ->
                getSelectedSortDirectionUseCase.invoke().map { sortDirection ->
                    getSortedNotes(notes, sortType, sortDirection)
                }
            }
        }
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
}
