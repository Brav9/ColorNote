package com.khalbro.colornote.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khalbro.colornote.data.local.entity.SelectedSortDirection

@Dao
interface SelectedSortDirectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSortDirection(selectedSortDirection: SelectedSortDirection)

    @Query("SELECT * from selected_sort_direction")
    fun getSortDirection(): LiveData<SelectedSortDirection>

    @Query("SELECT * from selected_sort_direction")
    suspend fun getSortDirectionSuspend(): SelectedSortDirection?
}