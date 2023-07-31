package com.khalbro.colornote.domain.usecase

import com.khalbro.colornote.domain.repository.InfoNoteRepository

class DeleteNoteUseCase(private val infoNoteRepository: InfoNoteRepository) {

    suspend operator fun invoke(id: Long) {
        infoNoteRepository.deleteInfoNote(id)
    }
}