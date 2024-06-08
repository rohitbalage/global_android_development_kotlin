package com.rrbofficial.androiduipracticekotlin.Notifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_CHANNEL_ID

class NotificationApp : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()


        // calling default Notification channel
        createNotificationChannel()
    }

    @RequiresApi(Build.VERSION_CODES.O)

    private fun createNotificationChannel()
    {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            // default Notification Channel
            val defaultNotification = NotificationChannel(
                NOTIFICATION_DEFAULT_CHANNEL_ID,
                "Default Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This is the default notification channel"
                lightColor = Color.GREEN
            }


            // High Notification Channel
            val highNotification = NotificationChannel(
                NOTIFICATION_HIGH_CHANNEL_ID,
                "High Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is the HIGH notification channel"
                lightColor = Color.GREEN
            }


            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(defaultNotification)
            notificationManager.createNotificationChannel(highNotification)
        }

    }
}