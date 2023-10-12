package com.khalbro.colornote.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.khalbro.colornote.data.local.SelectedSortDirectionDao
import com.khalbro.colornote.data.local.SelectedSortTypeDao
import com.khalbro.colornote.data.local.entity.SelectedSortDirection
import com.khalbro.colornote.data.local.entity.SelectedSortType
import com.khalbro.colornote.domain.models.SortType
import com.khalbro.colornote.domain.repository.SortNotesRepository

class SelectedSortNotesRepositoryImpl(
    private val selectedSortTypeDao: SelectedSortTypeDao,
    private val selectedSortDirectionDao: SelectedSortDirectionDao
) : SortNotesRepository {

    override   fun getSelectedSortDirection(): LiveData<SelectedSortDirection> {
        return selectedSortDirectionDao.getSortDirection()
    }

    override suspend fun getSelectedSortDirectionSuspend(): SelectedSortDirection? {
       return  selectedSortDirectionDao.getSortDirectionSuspend()
    }

    override  fun getSelectedSortType(): LiveData<SortType> {
        return selectedSortTypeDao.getSortType().map {
            it.sortType
        }
    }

    override suspend fun changeSortTypeNotes(sortTypeNotes: SortType) {
        val sortType = SelectedSortType(sortType = sortTypeNotes)
        selectedSortTypeDao.insertSortType(sortType)
    }

    override suspend fun changeSortDirectionNotes(sortDirectionNotes: SelectedSortDirection) {
        selectedSortDirectionDao.insertSortDirection(sortDirectionNotes)
    }
}



