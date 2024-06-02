package com.rrbofficial.androiduipracticekotlin

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        // Animate logo and text
        animateLogoAndText()

        // Delay for 2 seconds and then move to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000) // 2000 milliseconds = 2 seconds


        // for gif image
        val gifImageView: ImageView = findViewById(R.id.gifsplash)
        Glide.with(this)
            .load(R.drawable.androideating)
            .into(gifImageView)
    }

    private fun animateLogoAndText() {
        val logo = findViewById<ImageView>(R.id.splash_image)
        val text = findViewById<TextView>(R.id.splash_text)
        val fadeInDuration = 1000L // Duration of the fade-in animation in milliseconds

        // Animate logo fading in
        ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f).apply {
            duration = fadeInDuration
            start()
        }

        // Animate text fading in
        ObjectAnimator.ofFloat(text, "alpha", 0f, 1f).apply {
            duration = fadeInDuration
            start()
        }
    }
}
