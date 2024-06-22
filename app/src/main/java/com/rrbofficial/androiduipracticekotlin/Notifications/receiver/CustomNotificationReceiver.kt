package com.rrbofficial.androiduipracticekotlin.Notifications.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant

class CustomNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context, "Custom Notification is clicked", Toast.LENGTH_LONG).show()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.cancel(AppConstant.NOTIFICATION_CUSTOM_STYLE_INTENT_ID)

    }
}