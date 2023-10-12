package com.khalbro.colornote.domain.usecase

import com.khalbro.colornote.data.local.entity.SelectedSortDirection
import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.domain.repository.SortNotesRepository

class ChangeSortDirectionNotesUseCase(private val sortNotesRepository: SortNotesRepository) {

    suspend operator fun invoke() {
        val current = sortNotesRepository.getSelectedSortDirectionSuspend()
        val newSortDirection = if (current?.sortDirection == SortDirection.ASCENDING_SORT) {
            SortDirection.DESCENDING_SORT
        } else SortDirection.ASCENDING_SORT

        sortNotesRepository.changeSortDirectionNotes(SelectedSortDirection(newSortDirection))
    }
}