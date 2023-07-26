package com.khalbro.colornote.domain.usecase

import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository

class GetNoteByIdUseCase(private val infoNoteRepository: InfoNoteRepository) {

    suspend operator fun invoke(id: Long): InfoNote? {
        return infoNoteRepository.getNoteById(id)
    }
}