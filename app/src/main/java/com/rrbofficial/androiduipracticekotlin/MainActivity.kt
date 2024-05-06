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



    }

    fun goToUIComponents(view: View) {

        val buttonClick = findViewById<Button>(R.id.uicomponents)
        buttonClick.setOnClickListener {
            val intent = Intent(this, uiComponents::class.java)
            startActivity(intent)

        }
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
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
    fun gotToJetpackCompose(view: View) {
        val intent = Intent(this, jetpackCompose::class.java)
        startActivity(intent)

    }

    fun goToDatabases(view: View) {
        val intent = Intent(this, Databases::class.java)
        startActivity(intent)

    }

    fun goToAPi(view: View) {
        val intent = Intent(this, apis::class.java)
        startActivity(intent)
    }


}



