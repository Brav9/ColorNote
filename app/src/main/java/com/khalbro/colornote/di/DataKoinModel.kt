package com.khalbro.colornote.di

import com.khalbro.colornote.data.local.NotesDao
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import org.koin.dsl.module

val dataKoinModule = module {

    single<NotesDao> {
        NotesRoomDatabase.getDatabase(get()).notesDao()
    }

    single<InfoNoteRepository> {
        InfoNoteRepositoryImpl(notesDao = get())
    }
}