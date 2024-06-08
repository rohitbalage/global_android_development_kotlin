package com.rrbofficial.androiduipracticekotlin.Notifications.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_LOW_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_LOW_ID
import com.rrbofficial.androiduipracticekotlin.R

object NotificationHelper {
    fun defaultNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

        val defaultNotification = NotificationCompat.Builder(context, NOTIFICATION_DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon_vector_foreground)
            .setContentTitle(title)
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)  // required API level <26
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,  // Use the context parameter here instead of `this`
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(NOTIFICATION_DEFAULT_ID, defaultNotification)
    }

    fun highNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

        val highNotification = NotificationCompat.Builder(context, NOTIFICATION_HIGH_CHANNEL_ID )
            .setSmallIcon(R.drawable.notification_icon_vector_foreground)
            .setContentTitle(title)
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)  // required API level <26
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,  // Use the context parameter here instead of `this`
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify( NOTIFICATION_HIGH_ID, highNotification)
    }


    fun lowNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

        val lowNotification = NotificationCompat.Builder(context, NOTIFICATION_LOW_CHANNEL_ID )
            .setSmallIcon(R.drawable.notification_icon_vector_foreground)
            .setContentTitle(title)
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_LOW) // required API level <26
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,  // Use the context parameter here instead of `this`
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify( NOTIFICATION_LOW_ID, lowNotification)
    }
}
