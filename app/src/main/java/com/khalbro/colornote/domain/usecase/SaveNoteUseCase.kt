package com.khalbro.colornote.domain.usecase

import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository

class SaveNoteUseCase(private val infoNoteRepository: InfoNoteRepository) {

    fun execute(infoNote: InfoNote): Boolean {

        return infoNoteRepository.saveInfoNote(saveInfoNote = infoNote)

    }
}