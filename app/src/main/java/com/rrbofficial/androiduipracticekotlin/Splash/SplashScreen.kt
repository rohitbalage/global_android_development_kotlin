package com.rrbofficial.androiduipracticekotlin.Splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.rrbofficial.androiduipracticekotlin.BuildConfig
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithEmailAndPassword.Firebase_login
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithEmailAndPassword.Firebase_signup
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R

class SplashScreen : AppCompatActivity() {

    // Variable to store the intent to navigate after splash screen
    private var navigationIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Log the flavor and build type for debugging purposes
        Log.d("SplashScreen", "Current Flavor: ${BuildConfig.FLAVOR}")
        Log.d("SplashScreen", "Current Build Type: ${BuildConfig.BUILD_TYPE}")

        setContentView(R.layout.activity_splash_screen)
        animateLogoAndText()

        // Start the splash screen timer for 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            proceedToNextScreen() // Move to the next screen after 2 seconds
        }, 2000)

        // Handle dynamic links in parallel
        handleDynamicLink()

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

    private fun handleDynamicLink() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                    Log.d("DynamicLink", "Received deep link: $deepLink")

                    // Set navigation intent based on deep link
                    navigationIntent = when (deepLink?.path) {
                        "/special-offer" -> Intent(this, Firebase_login::class.java)
                        "/profile" -> Intent(this, Firebase_signup::class.java)
                        else -> Intent(this, MainActivity::class.java)
                    }
                } else {
                    // No deep link, proceed to MainActivity
                    navigationIntent = Intent(this, MainActivity::class.java)
                }
            }
            .addOnFailureListener(this) { e ->
                Log.w("DynamicLink", "getDynamicLink:onFailure", e)
                // Proceed to MainActivity in case of an error
                navigationIntent = Intent(this, MainActivity::class.java)
            }
    }

    // Function to navigate to the appropriate screen after splash screen delay
    private fun proceedToNextScreen() {
        // If dynamic link was processed, use that intent, else default to MainActivity
        if (navigationIntent == null) {
            navigationIntent = Intent(this, MainActivity::class.java)
        }
        startActivity(navigationIntent)
        finish() // Close the SplashScreen
    }
}
