package com.khalbro.colornote.di

import com.khalbro.colornote.domain.usecase.DeleteNoteUseCase
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import com.khalbro.colornote.domain.usecase.GetNoteByIdUseCase
import com.khalbro.colornote.domain.usecase.SaveNoteUseCase
import org.koin.dsl.module

val useCaseKoinModel = module {

    factory<DeleteNoteUseCase> {
        DeleteNoteUseCase(infoNoteRepository = get())
    }

    factory<GetAllNotesUseCase> {
        GetAllNotesUseCase(infoNoteRepository = get())
    }

    factory<SaveNoteUseCase> {
        SaveNoteUseCase(infoNoteRepository = get())
    }

    factory<GetNoteByIdUseCase> {
        GetNoteByIdUseCase(infoNoteRepository = get())
    }
}