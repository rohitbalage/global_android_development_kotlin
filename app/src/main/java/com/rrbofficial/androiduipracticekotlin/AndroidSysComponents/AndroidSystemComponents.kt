package com.rrbofficial.androiduipracticekotlin.AndroidSysComponents

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.BatteryManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityAndroidSystemComponentsBinding

class AndroidSystemComponents : AppCompatActivity() {

    private lateinit var binding: ActivityAndroidSystemComponentsBinding
    private lateinit var wifiManager: android.net.wifi.WifiManager
    private lateinit var batteryLevelReceiver: BroadcastReceiver
    private var backLightValue: Float = 0.5f
    private lateinit var mobileNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAndroidSystemComponentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Handle navigation icon click
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // brightness control listener
        binding.brightnessControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                backLightValue = progress / 100.0f
                binding.brightnessValue.text = backLightValue.toString()
                val layoutParams = window.attributes
                layoutParams.screenBrightness = backLightValue
                window.attributes = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something when the touch starts
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something when the touch stops
            }
        })


        // call number
        binding.callNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            mobileNumber = binding.EditTextmobileNumber.text.toString()

            if (mobileNumber.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill the fields", Toast.LENGTH_SHORT).show()
            } else {
                intent.data = Uri.parse("tel:$mobileNumber")
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Permission not granted.", Toast.LENGTH_SHORT).show()
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
                } else {
                    startActivity(intent)
                }
            }
        }

        // Initialize Wi-Fi manager
        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as android.net.wifi.WifiManager

        // Set click listeners
        binding.WifiONOFFBtn.setOnClickListener { toggleWifi() }
        binding.BluetoothONOFFBtn.setOnClickListener { toggleBluetooth() }

        // Load GIF image using Glide
        val gifImageView: ImageView = findViewById(R.id.gifComponents)
        Glide.with(this)
            .load(R.drawable.androidsysgif)
            .into(gifImageView)

        // Initialize battery level receiver
        batteryLevelReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                    binding.batterylevel.text = "Battery Level Remaining: $level%"
                    binding.progressbar.progress = level
                }
            }
        }
        // Register battery level receiver
        registerReceiver(batteryLevelReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

    }

    private fun toggleWifi() {
        if (wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = false
            binding.WifiONOFFBtn.text = getString(R.string.wifi_off)
        } else {
            wifiManager.isWifiEnabled = true
            binding.WifiONOFFBtn.text = getString(R.string.wifi_on)
        }
    }

    private fun toggleBluetooth() {
        // Your existing Bluetooth toggle logic
        // ...
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryLevelReceiver)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
