package com.khalbro.colornote.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    @ColumnInfo(name = "text") // необяз
    val text: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "color")
    val color: String
)
