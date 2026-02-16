package com.aliayali.personalfinanceapp.presentation.screens.notification

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.aliayali.personalfinanceapp.core.util.notification.DailyNotificationWorker
import com.aliayali.personalfinanceapp.data.local.datastore.NotificationDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val notificationDataStore: NotificationDataStore,
) : ViewModel() {

    init {
        viewModelScope.launch {
            notificationDataStore.notificationTimeFlow.collectLatest { (hour, minute) ->
                scheduleDailyNotification(hour, minute)
            }
        }
    }

    fun scheduleDailyNotification(hour: Int, minute: Int) {
        val now = Calendar.getInstance()

        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (before(now)) add(Calendar.DAY_OF_MONTH, 1)
        }

        val delay = target.timeInMillis - now.timeInMillis

        val request = PeriodicWorkRequestBuilder<DailyNotificationWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "daily_notification",
                ExistingPeriodicWorkPolicy.REPLACE,
                request
            )
    }

    fun updateNotificationTime(hour: Int, minute: Int) {
        viewModelScope.launch {
            notificationDataStore.saveNotificationTime(hour, minute)
        }
        scheduleDailyNotification(hour, minute)
    }
}