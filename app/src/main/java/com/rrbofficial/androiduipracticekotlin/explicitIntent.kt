package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class explicitIntent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent)

        // Receiving data from first activity

        // bundle of any time
        val bundle: Bundle? = intent.extras

        val str = bundle?.get("value")

        var txt : TextView = findViewById(R.id.receivedExplicitText)

        txt.text = str.toString()
    }
}