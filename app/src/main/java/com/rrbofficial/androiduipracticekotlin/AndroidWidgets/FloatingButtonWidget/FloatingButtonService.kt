package com.rrbofficial.androiduipracticekotlin.AndroidWidgets.FloatingButtonWidget

import android.app.Service
import android.app.Service.START_STICKY
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.rrbofficial.androiduipracticekotlin.R

class FloatingButtonService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Inflate the floating button layout
        floatingView = LayoutInflater.from(this).inflate(R.layout.floating_button_widget, null)

        // Create the WindowManager.LayoutParams to control the floating view's position
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // Set initial position
        params.gravity = Gravity.TOP or Gravity.START
        params.x = 100
        params.y = 100

        // Get WindowManager service
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        windowManager.addView(floatingView, params)

        // Add touch listener for drag movement
        floatingView.setOnTouchListener(object : View.OnTouchListener {
            private var initialX: Int = 0
            private var initialY: Int = 0
            private var initialTouchX: Float = 0f
            private var initialTouchY: Float = 0f

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Remember the initial position.
                        initialX = params.x
                        initialY = params.y
                        // Get the touch position.
                        initialTouchX = event.rawX
                        initialTouchY = event.rawY
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        // Calculate the new position.
                        params.x = initialX + (event.rawX - initialTouchX).toInt()
                        params.y = initialY + (event.rawY - initialTouchY).toInt()
                        // Update the layout with new X & Y coordinates
                        windowManager.updateViewLayout(floatingView, params)
                        return true
                    }
                }
                return false
            }
        })

        // Handle click on the floating button
        floatingView.findViewById<View>(R.id.floatingActionButton).setOnClickListener {
            // Perform any action, e.g., show 5-6 options
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (floatingView != null) windowManager.removeView(floatingView)
    }
}