package com.khalbro.colornote.di

import com.khalbro.colornote.domain.usecase.ChangeSortDirectionNotesUseCase
import com.khalbro.colornote.domain.usecase.ChangeSortTypeNotesUseCase
import com.khalbro.colornote.domain.usecase.DeleteNoteUseCase
import com.khalbro.colornote.domain.usecase.FillDatabaseWithDefaultValuesUseCase
import com.khalbro.colornote.domain.usecase.GetAllNotesUseCase
import com.khalbro.colornote.domain.usecase.GetNoteByIdUseCase
import com.khalbro.colornote.domain.usecase.GetSelectedSortDirectionUseCase
import com.khalbro.colornote.domain.usecase.GetSelectedSortTypeUseCase
import com.khalbro.colornote.domain.usecase.GetSortedNotesUseCase
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

    factory<ChangeSortTypeNotesUseCase> {
        ChangeSortTypeNotesUseCase(sortNotesRepository = get())
    }
    factory<ChangeSortDirectionNotesUseCase> {
        ChangeSortDirectionNotesUseCase(sortNotesRepository = get())
    }

    factory<GetSortedNotesUseCase> {
        GetSortedNotesUseCase(
            getAllNotesUseCase = get(),
            getSelectedSortDirectionUseCase = get(),
            getSelectedSortTypeUseCase = get()
        )
    }

    factory<GetSelectedSortTypeUseCase> {
        GetSelectedSortTypeUseCase(sortNotesRepository = get())
    }

    factory<GetSelectedSortDirectionUseCase> {
        GetSelectedSortDirectionUseCase(sortNotesRepository = get())
    }

    factory<FillDatabaseWithDefaultValuesUseCase> {
        FillDatabaseWithDefaultValuesUseCase(sortNotesRepository = get())
    }
}