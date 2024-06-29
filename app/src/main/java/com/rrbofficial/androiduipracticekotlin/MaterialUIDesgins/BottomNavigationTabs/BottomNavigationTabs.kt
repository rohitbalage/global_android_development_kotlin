package com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.BottomNavigationTabs

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rrbofficial.androiduipracticekotlin.R

class BottomNavigationTabs : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var textView: TextView
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bottom_navigation_tabs)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        textView = findViewById(R.id.text_view)
        frameLayout = findViewById(R.id.frame_container)

//        frameLayout.setBackgroundColor(Color.CYAN)
        textView.text = "Home"

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
//                frameLayout.setBackgroundColor(Color.CYAN)
                textView.text = "Home"
                true
            }
            R.id.navigation_favourite -> {
//                frameLayout.setBackgroundColor(Color.DKGRAY)
                textView.text = "Favourite"
                true
            }
            R.id.navigation_notification -> {
//                frameLayout.setBackgroundColor(Color.RED)
                textView.text = "Notification"
                true
            }
            else -> false
        }
    }
}
