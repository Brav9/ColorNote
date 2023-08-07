package com.khalbro.colornote.data.local.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2: Migration = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
       database.execSQL("ALTER TABLE Note ADD COLUMN date LONGER DEFAULT 0 NOT NULL")
    }

}