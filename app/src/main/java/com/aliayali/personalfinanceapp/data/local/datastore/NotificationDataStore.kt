package com.aliayali.personalfinanceapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.notificationDataStore by preferencesDataStore(name = "notification")

class NotificationDataStore @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {
    companion object {
        private val HOUR_KEY = intPreferencesKey("daily_notification_hour")
        private val MINUTE_KEY = intPreferencesKey("daily_notification_minute")
    }

    suspend fun saveNotificationTime(hour: Int, minute: Int) {
        context.notificationDataStore.edit { preferences ->
            preferences[HOUR_KEY] = hour
            preferences[MINUTE_KEY] = minute
        }
    }

    val notificationTimeFlow: Flow<Pair<Int, Int>> = context.notificationDataStore.data
        .map { preferences ->
            val hour = preferences[HOUR_KEY] ?: 21
            val minute = preferences[MINUTE_KEY] ?: 0
            hour to minute
        }
}