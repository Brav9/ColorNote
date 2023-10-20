package com.khalbro.colornote.domain.usecase

import androidx.lifecycle.LiveData
import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.domain.repository.SortNotesRepository

class GetSelectedSortDirectionUseCase(private val sortNotesRepository: SortNotesRepository) {

    operator fun invoke(): LiveData<SortDirection> {
        return sortNotesRepository.getSelectedSortDirection()
    }
}