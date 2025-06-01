package com.example.gradeup.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//fora para garantiar unica instancia
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencesds")

class PreferencesManager(private val context: Context) {

    companion object {
        private val SELECTED_CHIPS_KEY = stringSetPreferencesKey("selected_chips")
    }

    suspend fun saveSelectedChips(selectedChips: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CHIPS_KEY] = selectedChips
        }
    }

    fun getSelectedChips(): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[SELECTED_CHIPS_KEY] ?: emptySet()
        }
    }

    suspend fun clearFilters() {
        context.dataStore.edit { preferences ->
            preferences.remove(SELECTED_CHIPS_KEY)
        }
    }
}