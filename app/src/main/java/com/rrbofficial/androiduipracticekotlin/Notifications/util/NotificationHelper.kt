package com.rrbofficial.androiduipracticekotlin.Notifications.util

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.Notifications.receiver.AddToCartReceiver
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_ACTION_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_ACTION_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_BIG_PICTURE_STYLE_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_BIG_PICTURE_STYLE_INTENT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_BIG_TEXT_STYLE_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_BIG_TEXT_STYLE_INTENT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CONTENT_INTENT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CONTENT_INTENT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CUSTOM_SOUND_INTENT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_INBOX_STYLE_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_INBOX_STYLE_INTENT_ID
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


    fun customSoundNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

        // Create the pending intent
        val intent = Intent(context, AddToCartReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_MUTABLE)

        // Create the content intent
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(context, 0, contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val customSoundNotification = NotificationCompat.Builder(context, NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID )
            .setSmallIcon(R.drawable.notification_icon_vector_foreground)
            .setContentTitle(title)
            .setContentText(msg)
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // required API level <26
            .setCategory(NotificationCompat.CATEGORY_PROMO)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .addAction(R.drawable.action_add_tocart_foreground, "Add to cart", pendingIntent)
            .setSound(getUriFromResourceFile(context,R.raw.arabianmusicnotification))
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
        notificationManager.notify( NOTIFICATION_CUSTOM_SOUND_INTENT_ID, customSoundNotification)
    }

    fun bigTextStyleNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

//        // Create the pending intent
//        val intent = Intent(context, AddToCartReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
//            PendingIntent.FLAG_MUTABLE)
//
//        // Create the content intent
//        val contentIntent = Intent(context, MainActivity::class.java)
//        val contentPendingIntent = PendingIntent.getActivity(context, 0, contentIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT)

        val bigTextStyleNotification = NotificationCompat.Builder(context, NOTIFICATION_BIG_TEXT_STYLE_CHANNEL_ID )
            .setSmallIcon(R.drawable.app_icon_kotlin_background)
            .setStyle(NotificationCompat.BigTextStyle()
                .setBigContentTitle("This is Big Text Style Notification")
                .setSummaryText("Android Kotlin Practice Project")
                .bigText("Android Kotlin Practice Project is made by Rohit for all related mobile design practices. It contains various databasess such as AWS, mySQL, SQLite, Firebase, etc also contains various UI design patterns such as Material Design, Jetpack Compose, MVVM etc")
            )
            .setContentTitle(title)
            .setContentText(msg)
//            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // required API level <26
//            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setCategory(NotificationCompat.CATEGORY_PROMO)
            .setSound(getUriFromResourceFile(context,R.raw.arabianmusicnotification))
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
        notificationManager.notify( NOTIFICATION_BIG_TEXT_STYLE_INTENT_ID, bigTextStyleNotification)
    }


    fun inboxStyleNotification(context: Context, title: String, msg: String) {

        val notificationManager = NotificationManagerCompat.from(context)

//        // Create the pending intent
//        val intent = Intent(context, AddToCartReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
//            PendingIntent.FLAG_MUTABLE)
//
//        // Create the content intent
//        val contentIntent = Intent(context, MainActivity::class.java)
//        val contentPendingIntent = PendingIntent.getActivity(context, 0, contentIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT)

        val inboxStyleNotification = NotificationCompat.Builder(context, NOTIFICATION_INBOX_STYLE_CHANNEL_ID )
            .setSmallIcon(R.drawable.app_icon_kotlin_background)
            .setStyle(NotificationCompat.InboxStyle()
                .setBigContentTitle("Inbox style notifications")
                .setSummaryText("Reply S.P.A company")
                .addLine("Concept Reply - Focuses on the Internet of Things (IoT) and development of smart products, providing end-to-end solutions for connected devices.")
                .addLine("Light Reply -- company responsible for giving busineess solution to businesss")
                .addLine("Valorem Reply - Specializes in digital transformation and innovation consulting, helping businesses adopt advanced digital solutions.")
                .addLine("Aktive Reply - Specializes in digital experience and enterprise content management, delivering solutions for web content, digital asset management, and customer experience.")
                .addLine("Breed Reply - A venture capital arm that invests in and supports early-stage IoT startups, offering funding and expertise to accelerate growth.")
                .addLine("Data Reply - Provides services in big data analytics, machine learning, and artificial intelligence, helping companies leverage data for better decision-making.")
                .addLine("Glue Reply - Focuses on IT architecture, integration, and enterprise architecture management, ensuring seamless and efficient IT ecosystems.")
            )
            .setContentTitle(title)
            .setContentText(msg)
            .setOngoing(false)
//            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // required API level <26
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setCategory(NotificationCompat.CATEGORY_PROMO)
            .setSound(getUriFromResourceFile(context,R.raw.notificationdoorbell))
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
        notificationManager.notify( NOTIFICATION_INBOX_STYLE_INTENT_ID, inboxStyleNotification)
    }


    fun bigPictureStyleNotification(context: Context, title: String, msg: String, bitmap: Bitmap) {

        val notificationManager = NotificationManagerCompat.from(context)

        val bigPictureBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.rohit_edgewater)

        val largeIconBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.rohit2016)

        val bigPictureStyleNotification = NotificationCompat.Builder(context, NOTIFICATION_BIG_PICTURE_STYLE_CHANNEL_ID )
            .setSmallIcon(R.drawable.app_icon_kotlin_background)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .setBigContentTitle("This is Big Picture Style Notification")
                    .setSummaryText("Android Kotlin Practice Project")
                    .bigPicture(bigPictureBitmap)
                    .bigLargeIcon(bitmap)
            )
            .setContentTitle(title)
            .setContentText(msg)
            .setOngoing(false)
//            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // required API level <26
//            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setCategory(NotificationCompat.CATEGORY_PROMO)
            .setSound(getUriFromResourceFile(context,R.raw.notifictionhappybell))
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
        notificationManager.notify( NOTIFICATION_BIG_PICTURE_STYLE_INTENT_ID, bigPictureStyleNotification)
    }

    fun getUriFromResourceFile(context: Context, resourceId: Int): Uri {
        return Uri.parse("android.resource://$context.packageName/$resourceId")
    }
}
