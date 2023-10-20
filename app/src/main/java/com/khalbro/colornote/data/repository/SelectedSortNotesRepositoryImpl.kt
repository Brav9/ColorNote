package com.khalbro.colornote.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.khalbro.colornote.domain.models.SortDirection
import com.khalbro.colornote.domain.models.SortType
import com.khalbro.colornote.domain.repository.SortNotesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SelectedSortNotesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SortNotesRepository {

    private val SORT_DIRECTION = stringPreferencesKey("SORT_DIRECTION")
    private val SORT_TYPE = stringPreferencesKey("SORT_TYPE")

    override fun getSelectedSortDirection(): LiveData<SortDirection> {
        return dataStore.data.map { preferences ->
            val value: String = preferences[SORT_DIRECTION] ?: SortDirection.ASCENDING_SORT.name
            SortDirection.valueOf(value)
        }.asLiveData()
    }

    override suspend fun getSelectedSortDirectionSuspend(): SortDirection? {
        return dataStore.data.first().let { preferences ->
            val value: String = preferences[SORT_DIRECTION] ?: return null
            SortDirection.valueOf(value)
        }
    }

    override suspend fun getSelectedSortTypeSuspend(): SortType? {
        return dataStore.data.first().let { preferences ->
            val value: String = preferences[SORT_TYPE] ?: return null
            SortType.valueOf(value)
        }
    }

    override fun getSelectedSortType(): LiveData<SortType> {
        return dataStore.data.map { preferences ->
            val value: String = preferences[SORT_TYPE] ?: SortType.SORT_DATE.name
            SortType.valueOf(value)
        }.asLiveData()
    }

    override suspend fun changeSortTypeNotes(sortTypeNotes: SortType) {
        dataStore.edit { settings ->
            settings[SORT_TYPE] = sortTypeNotes.name
        }
    }

    override suspend fun changeSortDirectionNotes(sortDirectionNotes: SortDirection) {

        dataStore.edit { settings ->
            settings[SORT_DIRECTION] = sortDirectionNotes.name
        }
    }
}



