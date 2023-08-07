package com.khalbro.colornote.di

import com.khalbro.colornote.presentation.allnotes.NotesViewModel
import com.khalbro.colornote.presentation.editnote.EditNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelKoinModel = module {

    viewModel<NotesViewModel> {
        NotesViewModel(
            application = get(),
            deleteNoteUseCase = get(),
            getAllNotesUseCase = get()
        )
    }

    viewModel<EditNoteViewModel> {
        EditNoteViewModel(
            application = get(),
            savedStateHandle = get(),
            saveNoteUseCase = get(),
            getNoteByIdUseCase = get()
        )
    }
}