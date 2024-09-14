package com.rrbofficial.androiduipracticekotlin.AndroidAnimations

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R

class Animations : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animations)

        imageView = findViewById(R.id.imageView)

        // Start animation on the ImageView (pulsating effect)
        startImageViewAnimation()
    }

    // Method to animate the ImageView
    private fun startImageViewAnimation() {
        val scaleAnimation = ScaleAnimation(
            1f, 1.2f,  // Start and end scale for X axis
            1f, 1.2f,  // Start and end scale for Y axis
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot X
            Animation.RELATIVE_TO_SELF, 0.5f   // Pivot Y
        ).apply {
            duration = 500  // Animation duration in milliseconds
            repeatMode = Animation.REVERSE  // Reverse the animation after each cycle
            repeatCount = Animation.INFINITE  // Loop the animation infinitely
        }

        // Start the animation on the ImageView
        imageView.startAnimation(scaleAnimation)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
