package com.rrbofficial.androiduipracticekotlin.AndroidSysComponents

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityAndroidSystemComponentsBinding

class AndroidSystemComponents : AppCompatActivity() {

    private lateinit var binding: ActivityAndroidSystemComponentsBinding
    private lateinit var currentPhotoPath: String
    private lateinit var wifiManager: android.net.wifi.WifiManager

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
        private const val BLUETOOTH_PERMISSION_REQUEST_CODE = 102
    }

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
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show()
            return
        }

        if (bluetoothAdapter.isEnabled) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            bluetoothAdapter.disable()
            binding.BluetoothONOFFBtn.text = getString(R.string.bluetooth_off)
        } else {
            // Request Bluetooth permissions if not granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_ADMIN), BLUETOOTH_PERMISSION_REQUEST_CODE)
                return
            }

            bluetoothAdapter.enable()
            binding.BluetoothONOFFBtn.text = getString(R.string.bluetooth_on)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == BLUETOOTH_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toggleBluetooth()
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "Bluetooth permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Handle Bluetooth enable/disable request if needed
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
