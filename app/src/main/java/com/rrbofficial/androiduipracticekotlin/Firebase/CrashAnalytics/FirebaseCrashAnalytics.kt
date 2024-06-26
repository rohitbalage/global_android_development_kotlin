package com.rrbofficial.androiduipracticekotlin.Firebase.CrashAnalytics

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport
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

//        setKeysBasic("JasonTest");
//        logReportAndPrint()
//        logReportOnly()
//        setUserId()
//        logCaughtEx()
//        enableDebugMode()
//        forceACrash()

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

        // record an throwable exception
        // Uncomment the following block to track exceptions
        // try {
        //     methodThatThrows()
        // } catch (e: Exception) {
        //     FirebaseCrashlytics.getInstance().recordException(e)
        //     // handle your exception here
        // }


        //  enable Crashlytics debug logging to get more insights during development.
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(BuildConfig.DEBUG)



        // send user ID
        crashlytics.setUserId("user123456789")


        // log report only
        FirebaseCrashlytics.getInstance().log("message")


        binding.crashButton.setOnClickListener {
            // Log a message to Crashlytics
            crashlytics.log("Crash button clicked")
            // Set a user ID
            // crashlytics.setUserId("user123456789")
            // Force a crash
            throw RuntimeException("Test Crash") // Force a crash
        }

        binding.crashAppButton.setOnClickListener {
            val crashString: String? = null
            val length = crashString!!.length // This will cause a NullPointerException
            crashlytics.log("null pointer exception is recorded here")
        }

        binding.IndexOutOfBoundsButton.setOnClickListener()
        {
            val array = arrayOf(1, 2, 3)
            val element = array[10] // This will cause an IndexOutOfBoundsException
            crashlytics.log("index out of bound exception is recorded here")
        }

        binding.ArithmeticExceptionCrashButton.setOnClickListener()
        {
            val result = 10 / 0 // This will cause an ArithmeticException (divide by zero)
            crashlytics.log("arithmetic exception exception is recorded here")
        }

        binding.classCastExceptionBtn.setOnClickListener()
        {
            val obj: Any = "string"
            val number = obj as Int // This will cause a ClassCastException
            crashlytics.log("class cast exception is recorded here")
        }
        binding.stackOverflowErrorBtn.setOnClickListener(){
            fun recursiveFunction() {
                recursiveFunction()
            }
            recursiveFunction() // This will cause a StackOverflowError
            crashlytics.log("stack overflow exception is recorded here")
        }
        binding.illegalArgumentExceptionBtn.setOnClickListener(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel("", "", NotificationManager.IMPORTANCE_UNSPECIFIED)
                getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
                crashlytics.log("illegal argument is recorded here")
            }
        }

    }

}
