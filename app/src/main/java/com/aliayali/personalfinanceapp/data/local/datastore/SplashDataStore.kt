package com.aliayali.personalfinanceapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.splashDataStore by preferencesDataStore(name = "settings")

class SplashDataStore @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {
    private val splashKey = booleanPreferencesKey("show_splash")

    val showSplash: Flow<Boolean> = context.splashDataStore.data
        .map { prefs -> prefs[splashKey] ?: true }

    suspend fun setSplashShown() {
        context.splashDataStore.edit { prefs ->
            prefs[splashKey] = false
        }
    }
}
