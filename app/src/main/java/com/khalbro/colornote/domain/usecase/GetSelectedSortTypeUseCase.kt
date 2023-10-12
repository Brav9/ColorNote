package com.khalbro.colornote.domain.usecase

import androidx.lifecycle.LiveData
import com.khalbro.colornote.domain.models.SortType

import com.khalbro.colornote.domain.repository.SortNotesRepository

class GetSelectedSortTypeUseCase(private val sortNotesRepository: SortNotesRepository) {

    operator fun invoke(): LiveData<SortType> {
        return sortNotesRepository.getSelectedSortType()
    }
}