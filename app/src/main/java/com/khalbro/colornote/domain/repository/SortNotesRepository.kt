package com.khalbro.colornote.domain.repository

import androidx.lifecycle.LiveData
import com.khalbro.colornote.data.local.entity.SelectedSortDirection
import com.khalbro.colornote.domain.models.SortType

interface SortNotesRepository {

    fun getSelectedSortDirection(): LiveData<SelectedSortDirection>

    suspend fun getSelectedSortDirectionSuspend(): SelectedSortDirection?

    fun getSelectedSortType(): LiveData<SortType>

    suspend fun changeSortTypeNotes(sortTypeNotes: SortType)

    suspend fun changeSortDirectionNotes(sortDirectionNotes: SelectedSortDirection)

}