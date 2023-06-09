package com.khalbro.colornote.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.khalbro.colornote.data.local.NotesDao
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.repository.InfoNoteRepository

class InfoNoteRepositoryImpl(private val notesDao: NotesDao) : InfoNoteRepository {

    override suspend fun saveInfoNote(saveInfoNote: InfoNote) {
        val note = Note(
            id = saveInfoNote.id,
            title = saveInfoNote.noteTitle,
            text = saveInfoNote.textNote
        )
        notesDao.insertNote(note)
    }

    override suspend fun deleteInfoNote(id: Long) {
        notesDao.deleteNote(id)
    }

    override fun getAllNotesInfoNote(): LiveData<List<Note>> {
        return notesDao.getAllNotes().map { listOfNotes ->
            listOfNotes.map {
                Note(
                    id = it.id,
                    text = it.text,
                    title = it.title
                )
            }
        }
    }

    override suspend fun getNoteById(id: Long): InfoNote? {
        val getNote = notesDao.getNoteById(id)
        return if (getNote != null) InfoNote(
            id = getNote.id,
            textNote = getNote.text,
            noteTitle = getNote.title
        ) else null
    }
}