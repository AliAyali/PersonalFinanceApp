package com.aliayali.personalfinanceapp.presentation.screens.notification

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import com.aliayali.personalfinanceapp.core.util.notification.DailyNotificationWorker
import com.aliayali.personalfinanceapp.data.local.datastore.NotificationDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

@HiltViewModel
class NotificationViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val notificationDataStore: NotificationDataStore,
) : ViewModel() {

    fun scheduleDailyNotification(hour: Int, minute: Int) {
        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (before(now)) add(Calendar.DAY_OF_MONTH, 1)
        }

        val delay = target.timeInMillis - now.timeInMillis

        val request = OneTimeWorkRequestBuilder<DailyNotificationWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "daily_notification",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    fun updateNotificationTime(hour: Int, minute: Int) {
        viewModelScope.launch {
            notificationDataStore.saveNotificationTime(hour, minute)
        }
        scheduleDailyNotification(hour, minute)
    }

    fun cancelDailyNotification() {
        WorkManager.getInstance(context).cancelUniqueWork("daily_notification")
    }
}