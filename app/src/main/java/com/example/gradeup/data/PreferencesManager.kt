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

    suspend fun preferencesSave(key: String, value: Boolean){ //Suspend por o edit é função suspensa
        val preferencesKey = booleanPreferencesKey(key) //salvar de forma para dataStore possa ler
        context.dataStore.edit { pref -> pref[preferencesKey] = value }
    }

    suspend fun preferencesRead(key: String): Boolean{
        val preferencesKey = booleanPreferencesKey(key)
        val data = context.dataStore.data.first() //first é a maneira da acessar o data store
        return data[preferencesKey] ?: false
    }

    suspend fun preferencesRemove(key: String, value: Boolean){
        val preferencesKey = booleanPreferencesKey(key)
        context.dataStore.edit { pref -> pref.remove(preferencesKey) }
    }


    suspend fun saveSelectedChips(selectedChips: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CHIPS_KEY] = selectedChips
        }
    }

    // Recuperar chips selecionados
    fun getSelectedChips(): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[SELECTED_CHIPS_KEY] ?: emptySet()
        }
    }

    // Limpar filtros (opcional)
    suspend fun clearFilters() {
        context.dataStore.edit { preferences ->
            preferences.remove(SELECTED_CHIPS_KEY)
        }
    }
}