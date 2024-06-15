package com.rrbofficial.androiduipracticekotlin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.AWS.AWS
import com.rrbofficial.androiduipracticekotlin.AndroidSysComponents.AndroidSystemComponents
import com.rrbofficial.androiduipracticekotlin.AndroidSysComponents.AndroidUIWidgets
import com.rrbofficial.androiduipracticekotlin.GoogleMaps.GoogleMaps
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.JetpackCompose
import com.rrbofficial.androiduipracticekotlin.Notifications.Notifications
import com.rrbofficial.androiduipracticekotlin.Security.AndroidSecurity
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data binding initialization
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set click listeners for all buttons using binding object
        binding.uicomponents.setOnClickListener(this)
        binding.Kotlincoroutines.setOnClickListener(this)
        binding.JetpackCompose.setOnClickListener(this)
        binding.APisbtn.setOnClickListener(this)
        binding.databases.setOnClickListener(this)
        binding.googlemaps.setOnClickListener(this)
        binding.fragments.setOnClickListener(this)
        binding.GotoAnimations.setOnClickListener(this)
        binding.Kotlincoroutines.setOnClickListener(this)
        binding.GotoNotifications.setOnClickListener(this)
        binding.GotoAnimations.setOnClickListener(this)
        binding.GotoAWS.setOnClickListener(this)
        binding.GoToAndroidSecurity.setOnClickListener(this)
        binding.AritificalIntelligence.setOnClickListener(this)
        binding.IOTandThings.setOnClickListener(this)
        binding.AdvanceUIComponets.setOnClickListener(this)
        binding.AndroidSystemComponents.setOnClickListener(this)
        binding.GotoMachineLearning.setOnClickListener(this)



        // All About Android Activity Life cycle
        // override fun onStart() {
        //     super.onStart()
        //     Toast.makeText(this,"onStart() is callled" , Toast.LENGTH_LONG).show()
        // }

        // override fun onResume() {
        //     Toast.makeText(this,"onResume() is callled" , Toast.LENGTH_LONG).show()
        //     super.onResume()
        // }

        // override fun onPause() {
        //     Toast.makeText(this,"onPause() is callled" , Toast.LENGTH_LONG).show()
        //     super.onPause()
        // }

        // override fun onDestroy() {
        //     super.onDestroy()
        //     Toast.makeText(this,"onDestroy() is callled" , Toast.LENGTH_LONG).show()
        // }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.uicomponents -> {
                val intent = Intent(this, UIComponents::class.java)
                startActivity(intent)
                finish()
            }

            R.id.Kotlincoroutines -> {
                val intent = Intent(this, KotlinCoroutines::class.java)
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

            R.id.Kotlincoroutines -> {
                val intent = Intent(this, KotlinCoroutines::class.java)
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

            R.id.GotoAWS -> {
                val intent = Intent(this, AWS::class.java)
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
                val intent = Intent(this,MachineLearning::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)

        // Set dialog title and message
        builder.setTitle("Confirmation")
            .setMessage("Are you sure you want to close this activity?")

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
