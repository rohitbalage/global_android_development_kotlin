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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Log the flavor and build type for debugging purposes
        Log.d("SplashScreen", "Current Flavor: ${BuildConfig.FLAVOR}")
        Log.d("SplashScreen", "Current Build Type: ${BuildConfig.BUILD_TYPE}")

        setContentView(R.layout.activity_splash_screen)
        animateLogoAndText()

        // Handle dynamic links
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

                    // Redirect to different activities based on the deep link
                    when (deepLink?.path) {
                        "/special-offer" -> {
                            // Navigate to SpecialOfferActivity
                            val intent = Intent(this, Firebase_login::class.java)
                            startActivity(intent)
                        }
                        "/profile" -> {
                            // Navigate to ProfileActivity
                            val intent = Intent(this, Firebase_signup::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            // Default behavior, navigate to MainActivity
                            proceedToMainActivity()
                        }
                    }

                    // Finish SplashScreen so it doesn't stay in the back stack
                    finish()

                } else {
                    // No deep link, proceed to the main activity
                    proceedToMainActivity()
                }
            }
            .addOnFailureListener(this) { e ->
                Log.w("DynamicLink", "getDynamicLink:onFailure", e)
                proceedToMainActivity() // Proceed as normal if there's an error
            }
    }

    private fun proceedToMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000) // Delay to show splash screen for 3 seconds
    }
}
