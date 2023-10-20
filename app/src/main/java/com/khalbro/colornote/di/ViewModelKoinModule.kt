package com.khalbro.colornote.di

import com.khalbro.colornote.presentation.MainViewModel
import com.khalbro.colornote.presentation.allnotes.NotesViewModel
import com.khalbro.colornote.presentation.editnote.EditNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelKoinModel = module {

    viewModel<NotesViewModel> {
        NotesViewModel(
            getSelectedSortDirectionUseCase = get(),
            deleteNoteUseCase = get(),
            changeSortTypeNotesUseCase = get(),
            changeSortDirectionUseCase = get(),
            getSortedNotesUseCase = get()
        )
    }

    viewModel<EditNoteViewModel> {
        EditNoteViewModel(
            savedStateHandle = get(),
            saveNoteUseCase = get(),
            getNoteByIdUseCase = get()
        )
    }
    viewModel<MainViewModel> {
        MainViewModel(
            fillDatabaseWithDefaultValuesUseCase = get()
        )
    }
}