package com.khalbro.colornote.domain.usecase

import androidx.lifecycle.LiveData
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository

class GetAllNotesUseCase(private val infoNoteRepository: InfoNoteRepository) {

    suspend operator fun invoke(): LiveData<List<Note>> {

        return infoNoteRepository.getAllNotesInfoNote()
    }
}