package com.rrbofficial.androiduipracticekotlin

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.rrbofficial.androiduipracticekotlin.AchitecturePatterns.ArchitecturePatternsActivity
import com.rrbofficial.androiduipracticekotlin.AdvancedUIWidgets.AndroidUIWidgets
import com.rrbofficial.androiduipracticekotlin.AndroidSysComponents.AndroidSystemComponents
import com.rrbofficial.androiduipracticekotlin.GoogleMaps.GoogleMaps
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.JetpackCompose
import com.rrbofficial.androiduipracticekotlin.KotlinDSA.KotlinDSAActivity
import com.rrbofficial.androiduipracticekotlin.KotlinFundamentalsAndDSA.KotlinDSAAndFundamentals
import com.rrbofficial.androiduipracticekotlin.MaterialUIDesgins.MaterialUIComponents
import com.rrbofficial.androiduipracticekotlin.Notifications.Notifications
import com.rrbofficial.androiduipracticekotlin.PaymentIntegration.PaymentIntegrationActivity
import com.rrbofficial.androiduipracticekotlin.Security.AndroidSecurity
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityMainBinding
import timber.log.Timber

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
        Manifest.permission.READ_CONTACTS
    )

    // Request code for permissions
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Timber Logs
        Timber.d("onCreate called Android NAFTA")

        // Handle dynamic links

        // Handle the dynamic link
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get the deep link from result (if there is one)
                val deepLink = pendingDynamicLinkData?.link
                deepLink?.let {
                    // Handle the deep link URL
                    // For example, navigate to a specific screen
                    // Example: if (it.toString().contains("some_path")) { navigateToSomeScreen() }
                }
            }
            .addOnFailureListener(this) { e ->
                // Handle failure
            }


        // First Run dialogue
        if (isFirstRun()) {
            showWelcomeDialog()
            setFirstRunFlag()
        }

        // Check for permissions
        if (!hasPermissions(this, *PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE)
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
                    // Handle home click
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
            }
            drawerLayout.closeDrawers()
            true
        }

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

    private fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        return permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val deniedPermissions = permissions.filterIndexed { index, _ ->
                grantResults[index] != PackageManager.PERMISSION_GRANTED
            }
            if (deniedPermissions.isNotEmpty()) {
                // Handle the case where some permissions were denied
                AlertDialog.Builder(this)
                    .setTitle("Permissions Required")
                    .setMessage("Please grant all permissions to continue using the app.")
                    .setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
                        ActivityCompat.requestPermissions(this, deniedPermissions.toTypedArray(), PERMISSION_REQUEST_CODE)
                    }
                    .setCancelable(false)
                    .show()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.uicomponents -> {
                val intent = Intent(this, MaterialUIComponents::class.java)
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
                finish()
            }
            R.id.fragments -> {
                val intent = Intent(this, Fragments::class.java)
                startActivity(intent)
            }
            R.id.googlemaps -> {
                val intent = Intent(this, GoogleMaps::class.java)
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
                finish()
            }
            R.id.GotoMachineLearning -> {
                val intent = Intent(this, MachineLearning::class.java)
                startActivity(intent)
                finish()
            }
            R.id.switchTheme -> {
                // Handle switchTheme changes
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
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        // Set dialog title and message
        builder.setTitle("CLOSE APP")
            .setMessage("Are you sure you want to close this app?")

        // Set "Yes" button and its listener
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
            // Close the activity
            finish()
            dialogInterface.dismiss()
        }

        // Set "No" button and its listener
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
            // Dismiss the dialog box
            dialogInterface.dismiss()
        }

        // Create the dialog
        val dialog: AlertDialog = builder.create()

        // Set background color
        dialog.window?.setBackgroundDrawableResource(R.color.violet)

        // Customize button colors
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(resources.getColor(R.color.white))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(resources.getColor(R.color.white))
        }

        // Show the dialog
        dialog.show()
    }
}
