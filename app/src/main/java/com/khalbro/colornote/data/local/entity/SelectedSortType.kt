package com.khalbro.colornote.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.khalbro.colornote.domain.models.SortType
import com.khalbro.colornote.presentation.allnotes.SortNotesConverter

@Entity(tableName = "selected_sort_type")
data class SelectedSortType(
    @TypeConverters(SortNotesConverter::class)
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sort_type")
    val sortType: SortType
)

