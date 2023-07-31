package com.khalbro.colornote.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.khalbro.colornote.data.local.NotesDao
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import kotlin.random.Random

class InfoNoteRepositoryImpl(private val notesDao: NotesDao) : InfoNoteRepository {

    override suspend fun saveInfoNote(saveInfoNote: InfoNote) {
        val note = Note(
            id = saveInfoNote.id,
            title = saveInfoNote.title,
            text = saveInfoNote.text,
            color = saveInfoNote.color
        )
        notesDao.insertNote(note)
    }

    override suspend fun deleteInfoNote(id: Long) {
        notesDao.deleteNote(id)
    }

    override fun getAllNotesInfoNote(): LiveData<List<InfoNote>> {
        return notesDao.getAllNotes().map { listOfNotes ->
            listOfNotes.map {
                InfoNote(
                    id = it.id,
                    text = it.text,
                    title = it.title,
                    color = it.color
                )
            }
        }
    }

    override suspend fun getNoteById(id: Long): InfoNote? {
        val getNote = notesDao.getNoteById(id)
        return if (getNote != null) InfoNote(
            id = getNote.id,
            text = getNote.text,
            title = getNote.title,
            color = getNote.color
        ) else null
    }
}