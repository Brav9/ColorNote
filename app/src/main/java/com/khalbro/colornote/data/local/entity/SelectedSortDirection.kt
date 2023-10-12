package com.khalbro.colornote.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.presentation.allnotes.SortNotesConverter

@Entity(tableName = "selected_sort_direction")
data class SelectedSortDirection(
    @TypeConverters(SortNotesConverter::class)
    @ColumnInfo(name = "sort_direction")
    val sortDirection: SortDirection,

    @PrimaryKey(autoGenerate = false)
    val id: Int = 1
)
