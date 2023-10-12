package com.khalbro.colornote.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khalbro.colornote.data.local.entity.SelectedSortType

@Dao
interface SelectedSortTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSortType(selectedSortType: SelectedSortType)

    @Query("SELECT * from selected_sort_type")
     fun getSortType(): LiveData<SelectedSortType>
}