package com.khalbro.colornote.data.repository

import com.khalbro.colornote.data.storage.InfoStorage
import com.khalbro.colornote.data.storage.models.Info
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository

class InfoNoteRepositoryImpl(private val infoStorage: InfoStorage): InfoNoteRepository {

    override fun saveInfoNote(saveInfoNote: InfoNote): Boolean {
        val info = Info(textNote = saveInfoNote.textNote)
        return infoStorage.saveInfo(info)
    }

    override fun addInfoNote() {
        TODO("Not yet implemented")
    }

    override fun closeInfoNote() {
        TODO("Not yet implemented")
    }

    override fun deleteInfoNote() {
        TODO("Not yet implemented")
    }

    override fun editInfoNote() {
        TODO("Not yet implemented")
    }

    override fun showInfoNote() {
        TODO("Not yet implemented")
    }

    override fun loadInfoNote(): InfoNote {
        TODO("Not yet implemented")
    }

    override fun uploadInfoNotes() {
        TODO("Not yet implemented")
    }
}