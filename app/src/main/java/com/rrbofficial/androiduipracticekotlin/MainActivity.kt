package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import android.widget.Toast
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


    }

    fun goToAPi(view: View) {
        val intent = Intent(this, apis::class.java)
        startActivity(intent)
    }


}