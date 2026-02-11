package com.aliayali.personalfinanceapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("theme_pref")
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    }

    val isDarkThemeFlow: Flow<Boolean> =
        context.dataStore.data.map { pref ->
            pref[DARK_THEME_KEY] ?: false
        }

    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { pref ->
            pref[DARK_THEME_KEY] = isDark
        }
    }
}