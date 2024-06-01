package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class AndroidSystemComponents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_android_system_components)

        // Initialize the Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set navigation icon
//        toolbar.setNavigationIcon(R.drawable.profilepic)

        // Handle navigation icon click
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        // for gif image
        val gifImageView: ImageView = findViewById(R.id.gifComponents)
        Glide.with(this)
            .load(R.drawable.androidsysgif)
            .into(gifImageView)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
