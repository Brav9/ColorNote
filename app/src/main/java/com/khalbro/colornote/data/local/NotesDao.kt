package com.khalbro.colornote.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khalbro.colornote.data.local.entity.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Long): Note?

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * from notes")
    fun getAllNotes(): LiveData<List<Note>>
}