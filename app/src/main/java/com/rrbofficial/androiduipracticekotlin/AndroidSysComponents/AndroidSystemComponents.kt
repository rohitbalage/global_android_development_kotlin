package com.rrbofficial.androiduipracticekotlin.AndroidSysComponents

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityAndroidSystemComponentsBinding
import java.io.File
import java.io.IOException

class AndroidSystemComponents : AppCompatActivity() {

    private lateinit var binding: ActivityAndroidSystemComponentsBinding
    private lateinit var wifiManager: android.net.wifi.WifiManager
    private lateinit var batteryLevelReceiver: android.content.BroadcastReceiver
    private var backLightValue: Float = 0.5f
    private lateinit var mobileNumber: String
    private lateinit var cameraManager: android.hardware.camera2.CameraManager
    private var cameraId: String? = null
    private var isFlashlightOn: Boolean = false

    // For recording functionality
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var mediaPlayer: MediaPlayer
    private var audioFilePath: String? = null
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAndroidSystemComponentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Handle navigation icon click
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // brightness control listener
        binding.brightnessControl.setOnSeekBarChangeListener(object :
            android.widget.SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: android.widget.SeekBar, progress: Int, fromUser: Boolean) {
                backLightValue = progress / 100.0f
                binding.brightnessValue.text = backLightValue.toString()
                val layoutParams = window.attributes
                layoutParams.screenBrightness = backLightValue
                window.attributes = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: android.widget.SeekBar) {
                // Do something when the touch starts
            }

            override fun onStopTrackingTouch(seekBar: android.widget.SeekBar) {
                // Do something when the touch stops
            }
        })

        // call number
        binding.callNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            mobileNumber = binding.EditTextmobileNumber.text.toString()

            if (mobileNumber.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please fill the mobile number",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                intent.data = Uri.parse("tel:$mobileNumber")
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Permission not granted.",
                        Toast.LENGTH_SHORT
                    ).show()
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        1
                    )
                } else {
                    startActivity(intent)
                }
            }
        }

        // Save number button click listener
        binding.saveNumber.setOnClickListener {
            saveNumber()
        }

        binding.showTemperature.setOnClickListener {
            try {
                val thermalFile = "/sys/class/thermal/thermal_zone0/temp"
                val temp = File(thermalFile).readText().trim().toFloat() / 1000
                Toast.makeText(this, "Device Temperature: $temp°C", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Unable to read device temperature", Toast.LENGTH_LONG).show()
            }
        }

        binding.showMemoryUsage.setOnClickListener {
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memoryInfo = ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(memoryInfo)
            Toast.makeText(this, "\"Available Memory: ${memoryInfo.availMem / (1024 * 1024)} MB\\n\" +\n" +
                    "                    \"Total Memory: ${memoryInfo.totalMem / (1024 * 1024)} MB\"", Toast.LENGTH_SHORT).show()
        }

        binding.showCPUInfo.setOnClickListener {
            val cpuInfo = Build.HARDWARE
            Toast.makeText(this, "CPU Info: $cpuInfo", Toast.LENGTH_SHORT).show()
        }

        binding.showSensorInfo.setOnClickListener {
            val sensorManager = getSystemService(Context.SENSOR_SERVICE) as android.hardware.SensorManager
            val sensors = sensorManager.getSensorList(android.hardware.Sensor.TYPE_ALL)
            val sensorInfo = sensors.joinToString("\n") { it.name }
            Toast.makeText(this,"Sensor: $sensorInfo", Toast.LENGTH_SHORT).show()
        }

        // Initialize camera manager
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as android.hardware.camera2.CameraManager
        try {
            cameraId = cameraManager.cameraIdList[0]
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

        // Flashlight toggle button listener
        binding.flashlightButton.setOnCheckedChangeListener { _, isChecked ->
            isFlashlightOn = isChecked
            switchFlashLight(isFlashlightOn)
        }

        // Initialize Wi-Fi manager
        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as android.net.wifi.WifiManager

        // Set click listeners
        binding.WifiONOFFBtn.setOnClickListener { toggleWifi() }
        binding.BluetoothONOFFBtn.setOnClickListener { toggleBluetooth() }

        // Load GIF image using Glide
        val gifImageView: ImageView = findViewById(R.id.gifComponents)
        com.bumptech.glide.Glide.with(this)
            .load(R.drawable.androidsysgif)
            .into(gifImageView)

        // Initialize battery level receiver
        batteryLevelReceiver = object : android.content.BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    val level = it.getIntExtra(android.os.BatteryManager.EXTRA_LEVEL, 0)
                    binding.batterylevel.text = "Battery Level Remaining: $level%"
                    binding.progressbar.progress = level
                }
            }
        }
        // Register battery level receiver
        registerReceiver(batteryLevelReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        // Initialize media recorder and player
        mediaRecorder = MediaRecorder()
        mediaPlayer = MediaPlayer()
        setupRecordingButtons()
    }

    private fun setupRecordingButtons() {
        // Start recording button click listener
        binding.startrecord.setOnClickListener {
            if (isRecording) {
                stopRecording()
            } else {
                startRecording()
            }
        }

        // Stop recording button click listener
        binding.stopRecording.setOnClickListener {
            stopRecording()
        }

        // Play recording button click listener
        binding.playRecording.setOnClickListener {
            playRecording()
        }

        // Stop playing recording button click listener
        binding.stopPlayingRecord.setOnClickListener {
            stopPlaying()
        }
    }

    private fun startRecording() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                1
            )
            return
        }

        try {
            audioFilePath = Environment.getExternalStorageDirectory().absolutePath +
                    "/RecordedAudio.3gp"
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder.setOutputFile(audioFilePath)

            mediaRecorder.prepare()
            mediaRecorder.start()

            isRecording = true
            binding.startrecord.text = "Recording..."
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording() {
        if (isRecording) {
            mediaRecorder.stop()
            mediaRecorder.release()
            isRecording = false
            binding.startrecord.text = "Start Recording"
            Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show()
        }
    }

    private fun playRecording() {
        try {
            mediaPlayer.setDataSource(audioFilePath)
            mediaPlayer.prepare()
            mediaPlayer.start()
            Toast.makeText(this, "Recording playing", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopPlaying() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            Toast.makeText(this, "Recording stopped playing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveNumber() {
        val intent = Intent(ContactsContract.Intents.Insert.ACTION)
        mobileNumber = binding.EditTextmobileNumber.text.toString()

        if (mobileNumber.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Please fill the mobile number",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            intent.type = ContactsContract.RawContacts.CONTENT_TYPE
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, mobileNumber)
            startActivity(intent)
        }
    }

    private fun switchFlashLight(status: Boolean) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId!!, status)
            }
            if (status) {
                binding.flashlightButton.textOn = "ON"
            } else {
                binding.flashlightButton.textOff = "OFF"
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
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
        if (isRecording) {
            mediaRecorder.stop()
            mediaRecorder.release()
        }
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
