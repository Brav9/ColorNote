package com.khalbro.colornote.di

import com.khalbro.colornote.data.local.NotesDao
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.local.SelectedSortDirectionDao
import com.khalbro.colornote.data.local.SelectedSortTypeDao
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.data.repository.SelectedSortNotesRepositoryImpl
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import com.khalbro.colornote.domain.repository.SortNotesRepository
import org.koin.dsl.module

val dataKoinModule = module {

    single<NotesDao> {
        NotesRoomDatabase.getDatabase(get()).notesDao()
    }
    single<SelectedSortTypeDao> {
        NotesRoomDatabase.getDatabase(get()).selectedSortTypeDao()
    }
    single<SelectedSortDirectionDao> {
        NotesRoomDatabase.getDatabase(get()).selectedSortDirectionDao()
    }

    single<InfoNoteRepository> {
        InfoNoteRepositoryImpl(notesDao = get())
    }

    single<SortNotesRepository> {
        SelectedSortNotesRepositoryImpl(
            selectedSortDirectionDao = get(),
            selectedSortTypeDao = get()
        )
    }
}