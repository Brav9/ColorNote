package com.khalbro.colornote.domain.repository

import androidx.lifecycle.LiveData
import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.domain.models.SortType

interface SortNotesRepository {

    fun getSelectedSortDirection(): LiveData<SortDirection>

    suspend fun getSelectedSortDirectionSuspend(): SortDirection?

    suspend fun getSelectedSortTypeSuspend(): SortType?

    fun getSelectedSortType(): LiveData<SortType>

    suspend fun changeSortTypeNotes(sortTypeNotes: SortType)

    suspend fun changeSortDirectionNotes(sortDirectionNotes: SortDirection)

}