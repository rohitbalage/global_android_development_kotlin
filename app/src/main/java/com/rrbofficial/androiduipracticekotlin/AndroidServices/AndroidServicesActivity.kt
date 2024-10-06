package com.rrbofficial.androiduipracticekotlin.AndroidServices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.AndroidServices.Service.MusicService
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityAndroidServicesBinding

class AndroidServicesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAndroidServicesBinding

    private val counterReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val counterValue = intent?.getIntExtra("counter", 0)
            binding.tvCounter.text = counterValue.toString() // Use binding to access views
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidServicesBinding.inflate(layoutInflater) // Inflate the binding
        setContentView(binding.root) // Set the content view to the root of the binding

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            startService(intent)
        }

        binding.btnStop.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            stopService(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(counterReceiver, IntentFilter("com.rrbofficial.androiduipracticekotlin.UPDATE_COUNTER"))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(counterReceiver)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
