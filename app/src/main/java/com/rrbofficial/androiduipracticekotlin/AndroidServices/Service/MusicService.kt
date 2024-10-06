package com.rrbofficial.androiduipracticekotlin.AndroidServices.Service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.rrbofficial.androiduipracticekotlin.AndroidServices.AndroidServicesActivity
import com.rrbofficial.androiduipracticekotlin.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var handler: Handler
    private var counter = 0
    private val UPDATE_INTERVAL = 1000L

    override fun onCreate() {
        super.onCreate()

        // Set up MediaPlayer with a song from the raw folder
        mediaPlayer = MediaPlayer.create(this, R.raw.skyfallmusic)
        mediaPlayer.isLooping = true

        handler = Handler()
        startCounter()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        // Start service as foreground service with a notification
        val notification = createNotification()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()  // Start the media player
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun startCounter() {
        handler.post(object : Runnable {
            override fun run() {
                counter++
                // Update counter in UI using broadcast
                val intent = Intent("com.rrbofficial.androiduipracticekotlin.UPDATE_COUNTER")
                intent.putExtra("counter", counter)
                sendBroadcast(intent)
                handler.postDelayed(this, UPDATE_INTERVAL)
            }
        })
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MusicServiceChannel",
                "Music Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, AndroidServicesActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, "MusicServiceChannel")
            .setContentTitle("Music Playing")
            .setContentText("The music service is running.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }
}
