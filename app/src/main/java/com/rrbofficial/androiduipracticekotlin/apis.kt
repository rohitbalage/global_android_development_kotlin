package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class apis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apis)


        val JsonPasingButton :Button = findViewById(R.id.JsonParsingBtn)


        JsonPasingButton.setOnClickListener()
        {
            val intent = Intent(this, JsonParsing::class.java)
            startActivity(intent)
        }
    }
}