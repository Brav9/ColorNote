package com.khalbro.colornote.domain.repository

import com.khalbro.colornote.domain.models.InfoNote

interface InfoNoteRepository {

    fun saveInfoNote(saveInfoNote: InfoNote): Boolean

    fun addInfoNote()

    fun closeInfoNote()

    fun deleteInfoNote()

    fun editInfoNote()

    fun showInfoNote()

    fun loadInfoNote(): InfoNote

    fun uploadInfoNotes()

}
