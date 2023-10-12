package com.khalbro.colornote.domain.usecase

import com.khalbro.colornote.domain.models.SortType
import com.khalbro.colornote.domain.repository.SortNotesRepository

class ChangeSortTypeNotesUseCase(private val sortNotesRepository: SortNotesRepository) {

    suspend operator fun invoke(sortNotes: SortType) {
         sortNotesRepository.changeSortTypeNotes(sortTypeNotes = sortNotes)
    }
}