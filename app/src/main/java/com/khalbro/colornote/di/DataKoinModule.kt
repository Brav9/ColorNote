package com.khalbro.colornote.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.khalbro.colornote.data.local.NotesDao
import com.khalbro.colornote.data.local.NotesRoomDatabase
import com.khalbro.colornote.data.local.SelectedSortDirectionDao
import com.khalbro.colornote.data.local.SelectedSortTypeDao
import com.khalbro.colornote.data.local.dataStore
import com.khalbro.colornote.data.repository.InfoNoteRepositoryImpl
import com.khalbro.colornote.data.repository.SelectedSortNotesRepositoryImpl
import com.khalbro.colornote.domain.repository.InfoNoteRepository
import com.khalbro.colornote.domain.repository.SortNotesRepository
import org.koin.dsl.module

val dataKoinModule = module {

    single<DataStore<Preferences>> {
        val context: Context = get()
        context.dataStore
    }

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
            dataStore = get()
        )
    }
}