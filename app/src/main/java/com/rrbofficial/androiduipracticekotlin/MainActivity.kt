package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.view.Menu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.Api

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val uicomponents = findViewById<Button>(R.id.uicomponents)
            val kotlinCoroutines = findViewById<Button>(R.id.Kotlincoroutines)
            val jetpackCompose = findViewById<Button>(R.id.JetpackCompose)
            val apis = findViewById<Button>(R.id.APisbtn)
            val databases = findViewById<Button>(R.id.databases)
            val googleMapsgo = findViewById<Button>(R.id.googlemaps)
            val Fragmentsgo = findViewById<Button>(R.id.fragments)


            kotlinCoroutines.setOnClickListener()
            {
                val intent = Intent(this, kotlinCoroutines::class.java)
                startActivity(intent)
            }

           jetpackCompose.setOnClickListener()
           {
               val intent = Intent(this, jetpackCompose::class.java)
               startActivity(intent)
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

            apis.setOnClickListener()
            {
                val intent = Intent(this, apis::class.java)
                startActivity(intent)
            }

            databases.setOnClickListener()
            {
                val intent = Intent(this, Databases::class.java)
                startActivity(intent)
            }

            uicomponents.setOnClickListener {
                val intent = Intent(this, uiComponents::class.java)
                startActivity(intent)
            }

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



