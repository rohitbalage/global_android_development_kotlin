package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class implicitIntent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent)


        // Receiving data from UI Components
        // bundble? of any type data will accept
//        val bundle : Bundle? = intent.extras
//        val value = bundle?.get("value")

        val intent = intent
        val str = intent.getStringExtra("value")

        var txt : TextView = findViewById(R.id.receiveImplicitText)

        txt.text = str

    }
}