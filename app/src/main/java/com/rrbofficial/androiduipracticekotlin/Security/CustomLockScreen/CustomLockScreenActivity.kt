package com.rrbofficial.androiduipracticekotlin.Security.CustomLockScreen

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.R

class CustomLockScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_lock_screen)

        // Set the activity to appear on top of the lock screen
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        // Check for overlay permission (to draw over other apps)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, 1234)
            } else {
                showLockScreen()
            }
        } else {
            showLockScreen()
        }

        // Setup the Enter button
        val enterButton = findViewById<Button>(R.id.enter_button)
        enterButton.setOnClickListener {
            finish() // Finish the activity and return to the default lock screen
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234) {
            if (Settings.canDrawOverlays(this)) {
                showLockScreen()
            } else {
                Toast.makeText(this, "Permission denied to draw over other apps", Toast.LENGTH_SHORT).show()
                finish() // Close the app if permission is not granted
            }
        }
    }

    private fun showLockScreen() {
        // Disable the default lock screen (will still show the system PIN)
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val keyguardLock = keyguardManager.newKeyguardLock("CustomLock")
        keyguardLock.disableKeyguard()

        // Wake up the screen
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(
            PowerManager.FULL_WAKE_LOCK or
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
                    PowerManager.ON_AFTER_RELEASE, "CustomLockScreen::WakeLock"
        )
        wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Release the wake lock when the activity is destroyed
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(
            PowerManager.FULL_WAKE_LOCK or
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
                    PowerManager.ON_AFTER_RELEASE, "CustomLockScreen::WakeLock"
        )
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
    }
}
