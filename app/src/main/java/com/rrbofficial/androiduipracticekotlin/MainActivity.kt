package com.rrbofficial.androiduipracticekotlin

import android.Manifest
import android.app.Dialog
import android.app.PictureInPictureParams
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Rational
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.documentfile.provider.DocumentFile
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.rrbofficial.androiduipracticekotlin.AchitecturePatterns.ArchitecturePatternsActivity
import com.rrbofficial.androiduipracticekotlin.AdvancedUIWidgets.AndroidUIWidgets
import com.rrbofficial.androiduipracticekotlin.AndroidAnimations.Animations
import com.rrbofficial.androiduipracticekotlin.AndroidServices.AndroidServicesActivity
import com.rrbofficial.androiduipracticekotlin.AndroidSysComponents.AndroidSystemComponents
import com.rrbofficial.androiduipracticekotlin.AndroidWidgets.AndroidHomeWidgetsActivity
import com.rrbofficial.androiduipracticekotlin.ExternalUILibraries.ExternalUILibrariesActivity
import com.rrbofficial.androiduipracticekotlin.GoogleAds.GoogleAdsActivity
import com.rrbofficial.androiduipracticekotlin.GoogleMaps.GoogleMaps
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.JetpackCompose
import com.rrbofficial.androiduipracticekotlin.KotlinFundamentalsAndDSA.KotlinDSAAndFundamentals
import com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.MaterialUIComponents
import com.rrbofficial.androiduipracticekotlin.Notifications.Notifications
import com.rrbofficial.androiduipracticekotlin.Notifications.util.NotificationHelper
import com.rrbofficial.androiduipracticekotlin.PaymentIntegration.PaymentIntegrationActivity
import com.rrbofficial.androiduipracticekotlin.Security.AndroidSecurity
import com.rrbofficial.androiduipracticekotlin.Security.CustomLockScreen.ScreenReceiver
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityMainBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var crashlytics: FirebaseCrashlytics
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private val PREFS_NAME = "AppPrefs"
    private val FIRST_RUN_KEY = "isFirstRun"
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Permissions to request
    private val PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.USE_BIOMETRIC,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.BLUETOOTH_CONNECT,
    )

    // Request code for permissions
    private val PERMISSION_REQUEST_CODE = 100
    private val DIRECTORY_PICKER_REQUEST_CODE = 1001
    private val REQUEST_OVERLAY_PERMISSION_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Timber Logs
        Timber.d("onCreate MainActivity Started")

         //dynamic link received
        // Get the deep link passed from SplashScreen
        val deepLink = intent.getStringExtra("deep_link")
        if (deepLink != null) {
            Timber.d("DynamicLink", "Deep link received in MainActivity: $deepLink")
        }


            // check first run
        checkFirstRun()

        // check location of the user
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)



        // Check for permissions
        if (!hasPermissions(this, *PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE)
        }

        //check for overlay permissions

        // Check if the app has permission to draw overlays
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                FancyToast.makeText(this,"Please allow us permission to stay on top of apps",
                    FancyToast.LENGTH_LONG,
                    FancyToast.INFO,true).show()
                // If permission is not granted, request the user to grant permission
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )

                startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION_CODE)
            } else {
                // If permission is granted, proceed to start the lock screen service
                startLockScreenService()
            }
        } else {
            // For Android versions lower than M, directly start the lock screen service
            startLockScreenService()
        }


        // Check internet connectivity
        if (!isInternetAvailable(this)) {
            showInternetDialog()
        }

        // Initialize DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Set the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set the title of the CollapsingToolbarLayout
        binding.collapsingToolbar.title = "Android Practise by Rohit"
        binding.collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))

        // Load Header image using Glide
        updateHeaderImage()

        // Navigation drawer
        drawerLayout = binding.drawerLayout
        navView = binding.navView

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, KotlinDSAAndFundamentals::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_profile -> {
                    // Handle profile click
                }
                R.id.nav_settings -> {
                    // Handle settings click
                }
                R.id.nav_getlogs -> {
                    // Handle get logs click
                    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
                    startActivityForResult(intent, DIRECTORY_PICKER_REQUEST_CODE)
                }
            }
            drawerLayout.closeDrawers()
            true
        }


        //check for custom lock screen
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val receiver = ScreenReceiver()
        registerReceiver(receiver, filter)



        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)

        // Set the current theme based on saved preferences
        setInitialTheme()

        // Observe changes in isNightMode to apply the theme dynamically
        viewModel.isNightMode.observe(this) { isNightMode ->
            applyTheme(isNightMode)
            updateHeaderImage() // Update the header image when theme changes
        }

        // Handle switchTheme changes
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != viewModel.isNightMode.value) {
                viewModel.toggleTheme(isChecked)
            }
        }

        // Firebase crash analytics
        crashlytics = FirebaseCrashlytics.getInstance()

        // Set click listeners for all buttons using binding object
        binding.uicomponents.setOnClickListener(this)
        binding.Kotlindsaandfundamentals.setOnClickListener(this)
        binding.JetpackCompose.setOnClickListener(this)
        binding.APisbtn.setOnClickListener(this)
        binding.databases.setOnClickListener(this)
        binding.googlemaps.setOnClickListener(this)
        binding.fragments.setOnClickListener(this)
        binding.GotoAnimations.setOnClickListener(this)
        binding.GotoNotifications.setOnClickListener(this)
        binding.GotoArchitecturePatterns.setOnClickListener(this)
        binding.GoToAndroidSecurity.setOnClickListener(this)
        binding.AritificalIntelligence.setOnClickListener(this)
        binding.IOTandThings.setOnClickListener(this)
        binding.AdvanceUIComponets.setOnClickListener(this)
        binding.AndroidSystemComponents.setOnClickListener(this)
        binding.GotoMachineLearning.setOnClickListener(this)
        binding.GoToPaymentIntegration.setOnClickListener(this)
        binding.GoToGoogleAds.setOnClickListener(this)
        binding.GoToAndroidWidgets.setOnClickListener(this)
        binding.externaluilibraries.setOnClickListener(this)
        binding.GoToAndroidServices.setOnClickListener(this)
        binding.pipchip.setOnClickListener(this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DIRECTORY_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                // Save logs to the selected directory
                getAndSaveLogs(uri)
            }
        }
    }

    private fun getAndSaveLogs(directoryUri: Uri) {
        try {
            // Get logs
            val process = Runtime.getRuntime().exec("logcat -d")
            val bufferedReader = process.inputStream.bufferedReader()
            val currentLogs = bufferedReader.use { it.readText() }

            // Create a file in the selected directory
            val logFileName = "app_logs_GKA_${System.currentTimeMillis()}.txt"
            val documentUri = DocumentFile.fromTreeUri(this, directoryUri)?.createFile("text/plain", logFileName)

            documentUri?.let {
                contentResolver.openOutputStream(it.uri)?.use { outputStream ->
                    outputStream.write(currentLogs.toByteArray())
                    outputStream.flush()
                    Toast.makeText(this, "Logs saved to ${it.uri}", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: IOException) {
            Timber.e(e, "Error getting logs")
            Toast.makeText(this, "Failed to save logs", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkFirstRun() {
        if (isFirstRun()) {
            // Launch a coroutine to manage the flow
            GlobalScope.launch(Dispatchers.Main) {
                showLocationDialog()

                // Delay for 2 seconds before showing the location dialog
                delay(6000)
                showWelcomeDialog()
                setFirstRunFlag()

            }
        }
    }

    private fun showLocationDialog() {
        val dialog = LocationDialogFragment()
        dialog.show(supportFragmentManager, "LocationDialog")
    }

    private fun isFirstRun(): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(FIRST_RUN_KEY, true)
    }

    private fun setFirstRunFlag() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(FIRST_RUN_KEY, false)
        editor.apply()
    }

    private fun showWelcomeDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null)
        // Create the dialog
        val dialog = Dialog(this)
        dialog.setContentView(dialogView)
        // Handle the dismiss button
        val dismissButton = dialogView.findViewById<Button>(R.id.dismissButton)
        dismissButton.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }

    private fun setInitialTheme() {
        val isNightMode = sharedPreferences.getBoolean("NightMode", false)
        applyTheme(isNightMode)
        binding.switchTheme.isChecked = isNightMode
    }

    private fun applyTheme(isNightMode: Boolean) {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun updateHeaderImage() {
        val gifImageView: ImageView = binding.headerImage
        val gifResource = if (viewModel.isNightMode.value == true) {
            R.drawable.androiddarkgif // Dark theme GIF
        } else {
            R.drawable.androidheader // Light theme GIF
        }
        Glide.with(this)
            .load(gifResource)
            .into(gifImageView)
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    private fun showInternetDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet Connection")
            .setMessage("Please turn on your internet connection to use this app.")
            .setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun hasPermissions(context: MainActivity, vararg permissions: String): Boolean {
        return permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

// Method to start the LockScreenService
private fun startLockScreenService() {
    // Start the foreground service to ensure it's always running
    val serviceIntent = Intent(this, ScreenReceiver::class.java)
    startService(serviceIntent)
}

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val deniedPermissions = permissions.filterIndexed { index, _ ->
                grantResults[index] != PackageManager.PERMISSION_GRANTED
            }

        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.uicomponents -> {
                val intent = Intent(this, MaterialUIComponents::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left)
                finish()
            }
            R.id.externaluilibraries -> {
                    val intent = Intent(this, ExternalUILibrariesActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.Kotlindsaandfundamentals -> {
                val intent = Intent(this, KotlinDSAAndFundamentals::class.java)
                startActivity(intent)
                finish()
            }
            R.id.JetpackCompose -> {
                val intent = Intent(this, JetpackCompose::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_zoom_in, R.anim.anim_zoom_out)
                finish()
            }
            R.id.fragments -> {
                val intent = Intent(this, Fragments::class.java)
                startActivity(intent)
            }
            R.id.googlemaps -> {
                val intent = Intent(this, GoogleMaps::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_expand_out, R.anim.anim_expand_out)
            }

            R.id.GoToGoogleAds -> {
                val intent = Intent(this, GoogleAdsActivity::class.java)
                startActivity(intent)
            }

            R.id.APisbtn -> {
                val intent = Intent(this, Apis::class.java)
                startActivity(intent)
                finish()
            }
            R.id.databases -> {
                val intent = Intent(this, Databases::class.java)
                startActivity(intent)
                finish()
            }
            R.id.GotoAnimations -> {
                val intent = Intent(this, Animations::class.java)
                startActivity(intent)
                finish()
            }
            R.id.GotoNotifications -> {
                val intent = Intent(this, Notifications::class.java)
                startActivity(intent)
                finish()
            }
            R.id.GotoArchitecturePatterns -> {
                val intent = Intent(this, ArchitecturePatternsActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.GoToAndroidSecurity -> {
                val intent = Intent(this, AndroidSecurity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.AritificalIntelligence -> {
                val intent = Intent(this, ArtificialIntelligence::class.java)
                startActivity(intent)
                finish()
            }
            R.id.IOTandThings -> {
                val intent = Intent(this, InternetOfThings::class.java)
                startActivity(intent)
                finish()
            }
            R.id.AdvanceUIComponets -> {
                val intent = Intent(this, AndroidUIWidgets::class.java)
                startActivity(intent)
                finish()
            }
            R.id.AndroidSystemComponents -> {
                val intent = Intent(this, AndroidSystemComponents::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
                finish()
            }
            R.id.GotoMachineLearning -> {
                val intent = Intent(this, MachineLearning::class.java)
                startActivity(intent)
                finish()
            }
            R.id.switchTheme -> {
                binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked != viewModel.isNightMode.value) {
                        viewModel.toggleTheme(isChecked)
                    }
                }
            }
            R.id.GoToPaymentIntegration-> {
                val intent = Intent(this, PaymentIntegrationActivity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.GoToAndroidWidgets -> {
                val intent = Intent(this, AndroidHomeWidgetsActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_rotate_in, R.anim.anim_rotate_out)
                finish()
            }


            R.id.GoToAndroidServices -> {
                val intent = Intent(this, AndroidServicesActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_rotate_in, R.anim.anim_rotate_out)
                finish()
            }

            R.id.pipchip -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    enterPictureInPictureModeActivity()
                }
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun enterPictureInPictureModeActivity() {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y

        val ratio = Rational(width, height)
        val pipBuilder = PictureInPictureParams.Builder()
        pipBuilder.setAspectRatio(ratio).build()
        enterPictureInPictureMode(pipBuilder.build())

    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        // Handle additional logic if needed when the user leaves the activity
        val title = "You are in PIP mode"
        val msg = "PIP mode activated in GKA"
        NotificationHelper.lowNotification(this,title,msg)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("CLOSE APP")
            .setMessage("Are you sure you want to close this app?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
            finish()
            dialogInterface.dismiss()
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.color.violet)
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(resources.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(resources.getColor(R.color.white))
        }
        dialog.show()
    }
}
