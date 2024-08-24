package com.rrbofficial.androiduipracticekotlin.Splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rrbofficial.androiduipracticekotlin.BuildConfig
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.JetpackCompose
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Log the flavor and build type for debugging purposes
        Log.d("SplashScreen", "Current Flavor: ${BuildConfig.FLAVOR}")
        Log.d("SplashScreen", "Current Build Type: ${BuildConfig.BUILD_TYPE}")

            setContentView(R.layout.activity_splash_screen)
            animateLogoAndText()

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000) // 3000 milliseconds = 3 seconds

        val gifImageView: ImageView = findViewById(R.id.gifsplash)
        Glide.with(this)
            .load(R.drawable.androideating)
            .into(gifImageView)
    }

    private fun animateLogoAndText() {
        val logo = findViewById<ImageView>(R.id.splash_image)
        val text = findViewById<TextView>(R.id.splash_text)
        val fadeInDuration = 1000L

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
