package com.ahrorovk.myapplication.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences_name")
        val TASBIH_COUNTER_STATE = intPreferencesKey("tasbih_counter_state")
        val DATE_STATE = longPreferencesKey("date_state")
        val LANGUAGE_STATE = intPreferencesKey("language_state")
    }

    suspend fun updateTasbihCounterState(state: Int) {
        context.dataStore.edit { preferences ->
            preferences[TASBIH_COUNTER_STATE] = state
        }
    }

    suspend fun updateLanguageState(state: Int) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_STATE] = state
        }
    }

    suspend fun updateDateState(state: Long) {
        context.dataStore.edit { preferences ->
            preferences[DATE_STATE] = state
        }
    }

    val getTasbihCounterState = context.dataStore.data.map {
        it[TASBIH_COUNTER_STATE] ?: 0
    }
    val getDateState = context.dataStore.data.map {
        it[DATE_STATE] ?: 0
    }

    val getLanguageState = context.dataStore.data.map {
        it[LANGUAGE_STATE] ?: 0
    }
}