package com.khalbro.colornote.domain.repository

import androidx.lifecycle.LiveData
import com.khalbro.colornote.domain.models.InfoNote

interface InfoNoteRepository {

    suspend fun saveInfoNote(saveInfoNote: InfoNote)

    suspend fun deleteInfoNote(id: Long)

    fun getAllNotesInfoNote(): LiveData<List<InfoNote>>

    suspend fun getNoteById(id: Long): InfoNote?

}

