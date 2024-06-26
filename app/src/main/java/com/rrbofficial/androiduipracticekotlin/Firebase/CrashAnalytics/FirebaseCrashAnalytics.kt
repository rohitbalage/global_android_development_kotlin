package com.rrbofficial.androiduipracticekotlin.Firebase.CrashAnalytics

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.setCustomKeys
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityFirebaseCrashAnalyticsBinding

class FirebaseCrashAnalytics : AppCompatActivity() {

    private lateinit var binding: ActivityFirebaseCrashAnalyticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirebaseCrashAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        // Enable Crashlytics collection
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setCustomKeys {
            key("my_string_key", "foo") // String value
            key("my_bool_key", true) // boolean value
            key("my_double_key", 1.0) // double value
            key("my_float_key", 1.0f) // float value
            key("my_int_key", 1) // int value

            // custom key values
            key("current_level", 3)
            key("last_UI_action", "logged_in")
        }

        // track error with try and catch
        // Uncomment the following block to track exceptions
        // try {
        //     methodThatThrows()
        // } catch (e: Exception) {
        //     FirebaseCrashlytics.getInstance().recordException(e)
        //     // handle your exception here
        // }

        binding.crashButton.setOnClickListener {
            // Log a message to Crashlytics
            crashlytics.log("Crash button clicked")
            // Set a user ID if applicable
            // crashlytics.setUserId("user123456789")
            // Set custom keys
            crashlytics.setCustomKey("ButtonClicked", "Crash Button")
            // Force a crash
            throw RuntimeException("Test Crash") // Force a crash
        }

        binding.crashAppButton.setOnClickListener {
            val crashString: String? = null
            try {
                val length = crashString!!.length // This will cause a NullPointerException
            } catch (e: Exception) {
                crashlytics.recordException(e)
                crashlytics.setCustomKey("ErrorLocation", "crashAppButton OnClickListener")
            }
        }

        binding.IndexOutOfBoundsButton.setOnClickListener {
            val array = arrayOf(1, 2, 3)
            try {
                val element = array[10] // This will cause an IndexOutOfBoundsException
            } catch (e: Exception) {
                crashlytics.recordException(e)
                crashlytics.setCustomKey("ErrorLocation", "IndexOutOfBoundsButton OnClickListener")
            }
        }

        binding.ArithmeticExceptionCrashButton.setOnClickListener {
            try {
                val result = 10 / 0 // This will cause an ArithmeticException (divide by zero)
            } catch (e: Exception) {
                crashlytics.recordException(e)
                crashlytics.setCustomKey("ErrorLocation", "ArithmeticExceptionCrashButton OnClickListener")
            }
        }
    }
    }

