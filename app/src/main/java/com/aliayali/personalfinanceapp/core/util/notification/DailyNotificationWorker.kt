package com.aliayali.personalfinanceapp.core.util.notification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aliayali.personalfinanceapp.MainActivity
import com.aliayali.personalfinanceapp.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DailyNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return Result.success()
            }
        }
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val remoteViews = RemoteViews(applicationContext.packageName, R.layout.notification_custom)
        remoteViews.setTextViewText(
            R.id.textViewTitle,
            "\u200f📊 بررسی وضعیت مالی"
        )
        remoteViews.setTextViewText(
            R.id.textViewText,
            "\u200fگزارش امروزت رو چک کن و مدیریت هوشمند داشته باش 💰"
        )
        remoteViews.setTextViewText(
            R.id.textViewAppName,
            applicationContext.getString(R.string.app_name)
        )
        remoteViews.setImageViewResource(R.id.notificationIcon, R.drawable.ic_application)

        val notification = NotificationCompat.Builder(applicationContext, "daily_channel")
            .setSmallIcon(R.drawable.ic_application)
            .setCustomContentView(remoteViews)
            .setCustomBigContentView(remoteViews)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setShowWhen(true)
            .build()

        NotificationManagerCompat.from(applicationContext)
            .notify(System.currentTimeMillis().toInt(), notification)

        return Result.success()
    }
}