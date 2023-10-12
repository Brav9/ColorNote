package com.khalbro.colornote.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.data.local.entity.SelectedSortDirection
import com.khalbro.colornote.data.local.entity.SelectedSortType
import com.khalbro.colornote.presentation.allnotes.SortNotesConverter

@Database(
    entities = [Note::class, SelectedSortType::class, SelectedSortDirection::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SortNotesConverter::class)
abstract class NotesRoomDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
    abstract fun selectedSortTypeDao(): SelectedSortTypeDao
    abstract fun selectedSortDirectionDao(): SelectedSortDirectionDao

    companion object {
        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null

        fun getDatabase(context: Context): NotesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesRoomDatabase::class.java,
                    "notes_database"
                )
                    .addTypeConverter(SortNotesConverter())
//                    .addMigrations(MIGRATION_1_2)
//                    .addCallback(object : Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            db.execSQL("ALTER TABLE notes ADD COLUMN date INTEGER")
//                        }
//                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

//val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE notes ADD COLUMN date INTEGER")
//    }
//}
