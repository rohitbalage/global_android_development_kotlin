package com.rrbofficial.androiduipracticekotlin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gotouicomponents = findViewById<Button>(R.id.uicomponents)
        val kotlinCoroutines = findViewById<Button>(R.id.Kotlincoroutines)
        val gotojetpackCompose = findViewById<Button>(R.id.JetpackCompose)
        val gotoapis = findViewById<Button>(R.id.APisbtn)
        val databases = findViewById<Button>(R.id.databases)
        val googleMapsgo = findViewById<Button>(R.id.googlemaps)
        val Fragmentsgo = findViewById<Button>(R.id.fragments)


        kotlinCoroutines.setOnClickListener()
        {

        }

        gotojetpackCompose.setOnClickListener()
        {
            val intent = Intent(this, Fragments::class.java)
            startActivity(intent)
            finish()
        }

        Fragmentsgo.setOnClickListener()
        {
            val intent = Intent(this, Fragments::class.java)
            startActivity(intent)
        }

        googleMapsgo.setOnClickListener()
        {
            val intent = Intent(this, GoogleMaps::class.java)
            startActivity(intent)

        }

        gotoapis.setOnClickListener()
        {
            val intent = Intent(this, Apis::class.java)
            startActivity(intent)
            finish()
        }

        databases.setOnClickListener()
        {
            val intent = Intent(this, Databases::class.java)
            startActivity(intent)
            finish()
        }

        gotouicomponents.setOnClickListener {
            val intent = Intent(this, UIComponents::class.java)
            startActivity(intent)
            finish()
        }


        // All About Android Activity Life cycle
//    override fun onStart() {
//        super.onStart()
//        Toast.makeText(this,"onStart() is callled" , Toast.LENGTH_LONG).show()
//
//    }
//
//    override fun onResume() {
//        Toast.makeText(this,"onResume() is callled" , Toast.LENGTH_LONG).show()
//        super.onResume()
//    }
//
//    override fun onPause() {
//        Toast.makeText(this,"onPause() is callled" , Toast.LENGTH_LONG).show()
//        super.onPause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Toast.makeText(this,"onDestroy() is callled" , Toast.LENGTH_LONG).show()
//    }

        fun goToKotlinCoroutines(view: View) {


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





