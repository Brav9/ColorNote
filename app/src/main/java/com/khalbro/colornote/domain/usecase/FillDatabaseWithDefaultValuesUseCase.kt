package com.khalbro.colornote.domain.usecase

import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.domain.models.SortType
import com.khalbro.colornote.domain.repository.SortNotesRepository

class FillDatabaseWithDefaultValuesUseCase(private val sortNotesRepository: SortNotesRepository) {

    suspend operator fun invoke() {
        if (sortNotesRepository.getSelectedSortDirectionSuspend() == null
            && sortNotesRepository.getSelectedSortTypeSuspend() == null
        ) {
            sortNotesRepository.changeSortTypeNotes(SortType.SORT_DATE)
            sortNotesRepository.changeSortDirectionNotes(SortDirection.ASCENDING_SORT)
        }
    }
}