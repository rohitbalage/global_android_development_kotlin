package com.rrbofficial.androiduipracticekotlin.Notifications.util

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.Notifications.receiver.AddToCartReceiver
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_ACTION_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_ACTION_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CONTENT_INTENT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CONTENT_INTENT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_LOW_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_LOW_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_ONGOING_INTENT_ID
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.SplashScreen

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

    fun actionNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

        val intent = Intent(context, AddToCartReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_MUTABLE)

        val actionNotification = NotificationCompat.Builder(context, NOTIFICATION_ACTION_CHANNEL_ID )
            .setSmallIcon(R.drawable.notification_icon_vector_foreground)
            .setContentTitle(title)
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // required API level <26
            .setCategory(NotificationCompat.CATEGORY_PROMO)
            .addAction(R.drawable.action_add_tocart_foreground, "Add to cart", pendingIntent)
            .setAutoCancel(true)
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
        notificationManager.notify( NOTIFICATION_ACTION_ID, actionNotification)
    }

    fun contentIntentNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

        // Create the pending intent
        val intent = Intent(context, AddToCartReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_MUTABLE)

        // Create the content intent
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(context, 0, contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val contentIntentNotification = NotificationCompat.Builder(context, NOTIFICATION_CONTENT_INTENT_CHANNEL_ID )
            .setSmallIcon(R.drawable.notification_icon_vector_foreground)
            .setContentTitle(title)
            .setContentText(msg)
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // required API level <26
            .setCategory(NotificationCompat.CATEGORY_PROMO)
            .addAction(R.drawable.action_add_tocart_foreground, "Add to cart", pendingIntent)
            .setAutoCancel(true)
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
        notificationManager.notify( NOTIFICATION_CONTENT_INTENT_ID, contentIntentNotification)
    }

    fun onGoingNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

        // Create the pending intent
        val intent = Intent(context, AddToCartReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_MUTABLE)

        // Create the content intent
        val contentIntent = Intent(context, SplashScreen::class.java).apply {
           addCategory(Intent.CATEGORY_LAUNCHER)
            action = Intent.ACTION_MAIN
        }
        val contentPendingIntent = PendingIntent.getActivity(context, 0, contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)


        val onGoingNotification = NotificationCompat.Builder(context, NOTIFICATION_CONTENT_INTENT_CHANNEL_ID )
            .setSmallIcon(R.drawable.notification_icon_vector_foreground)
            .setContentTitle(title)
            .setContentText(msg)
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // required API level <26
            .setCategory(NotificationCompat.CATEGORY_PROMO)
            .addAction(R.drawable.action_add_tocart_foreground, "Add to cart", pendingIntent)
            .setOngoing(true)
            .setAutoCancel(false)
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
        notificationManager.notify( NOTIFICATION_ONGOING_INTENT_ID, onGoingNotification)
    }
}
