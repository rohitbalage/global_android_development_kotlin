package com.rrbofficial.androiduipracticekotlin.Notifications

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_ACTION_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_BIG_PICTURE_STYLE_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_BIG_TEXT_STYLE_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CONTENT_INTENT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DEFAULT_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_DOWNLOAD_STYLE_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_HIGH_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_INBOX_STYLE_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_LOW_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.Notifications.util.AppConstant.NOTIFICATION_ONGOING_CHANNEL_ID
import com.rrbofficial.androiduipracticekotlin.R

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


            // audio attributes for notification
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .build()


            // default Notification Channel
            val defaultNotification = NotificationChannel(
                NOTIFICATION_DEFAULT_CHANNEL_ID,
                "Default Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This is the default notification channel"
                lightColor = Color.GREEN
                // vibration for notification
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
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

            // low Notification Channel
            val lowNotification = NotificationChannel(
                NOTIFICATION_LOW_CHANNEL_ID,
                "Low Notification Channel",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "This is the low notification channel"
                lightColor = Color.GREEN
            }

            // action Notification Channel
            val actionNotification = NotificationChannel(
                NOTIFICATION_ACTION_CHANNEL_ID,
                "Action Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is the action notification channel"
                lightColor = Color.GREEN
            }

            // contentIntent Notification Channel
            val contentIntentNotification = NotificationChannel(
                NOTIFICATION_CONTENT_INTENT_CHANNEL_ID,
                "Content Intent Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is the content intent notification channel"
                lightColor = Color.RED
            }

            // contentIntent Notification Channel
            val onGoingNotification = NotificationChannel(
                NOTIFICATION_ONGOING_CHANNEL_ID,
                "OnGoing Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is the on going notification channel"
                lightColor = Color.RED
            }

            // custom sound Notification Channel
            val customSoundNotification = NotificationChannel(
                NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID ,
                "custom sound Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is custom sound notification channel"
                lightColor = Color.RED

                // set sound
                setSound(getUriFromResourceFile(this@NotificationApp,
                    R.raw.arabianmusicnotification), audioAttributes)

                //   vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            }

            // big text style Notification Channel
            val bigTextStyleNotification = NotificationChannel(
                NOTIFICATION_BIG_TEXT_STYLE_CHANNEL_ID ,
                "big text style Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is big text style notification channel"
                lightColor = Color.RED
                setSound(getUriFromResourceFile(this@NotificationApp,
                    R.raw.arabianmusicnotification), audioAttributes)
            }


            // inbox  style Notification Channel
            val inboxStyleNotification = NotificationChannel(
                NOTIFICATION_INBOX_STYLE_CHANNEL_ID ,
                "inbox style Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "inbox style notification channel"
                lightColor = Color.RED
                setSound(getUriFromResourceFile(this@NotificationApp,
                    R.raw.notificationdoorbell), audioAttributes)
            }

            // big picture  style Notification Channel
            val bigPictureStyleNotification = NotificationChannel(
                NOTIFICATION_BIG_PICTURE_STYLE_CHANNEL_ID ,
                "Big picture style Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "big picture style notification channel"

                lightColor = Color.RED
                setSound(getUriFromResourceFile(this@NotificationApp,
                    R.raw.notifictionhappybell), audioAttributes)
            }


            // big picture  style Notification Channel
            val downloadStyleNotification = NotificationChannel(
                NOTIFICATION_DOWNLOAD_STYLE_CHANNEL_ID ,
                "download style Notification Channel",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "download style notification channel"
                lightColor = Color.RED
            }







            val notificationManager = getSystemService(NotificationManager::class.java)
//            notificationManager.createNotificationChannel(defaultNotification)
//            notificationManager.createNotificationChannel(highNotification)

            // create a list of notification channels
            notificationManager.createNotificationChannels(listOf(
                defaultNotification,
                highNotification,
                lowNotification,
                actionNotification,
                contentIntentNotification,
                onGoingNotification,
                customSoundNotification,
                bigTextStyleNotification,
                inboxStyleNotification,
                bigPictureStyleNotification,
                downloadStyleNotification
            ))
        }
    }

   private fun getUriFromResourceFile(context: Context, resourceId: Int): Uri {
    return Uri.parse("android.resource://$packageName/$resourceId")
    }
}