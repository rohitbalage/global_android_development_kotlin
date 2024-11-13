package com.rrbofficial.androiduipracticekotlin.GoogleMaps

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityGoogleMapsOptionsBinding

class GoogleMapsOptions : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleMapsOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps_options)
        enableEdgeToEdge()
        // Initialize view binding
        binding = ActivityGoogleMapsOptionsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Set click listeners using binding
        binding.googlemapsbasics.setOnClickListener {
        val intent = Intent(this, GoogleMaps::class.java)
            startActivity(intent)

        }

        binding.placesSDK.setOnClickListener {

        }

        binding.geolocationSDK.setOnClickListener {

        }

    }
}