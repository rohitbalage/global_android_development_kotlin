package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Apis : AppCompatActivity() {
    private lateinit var retrofitbtn: Button
    private lateinit var volleybtn: Button
    private lateinit var ktorbtn: Button
    private lateinit var jsonparsingbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apis)


        val jsonParsing :Button = findViewById(R.id.JsonParsingBtn)
        val retrofitbtn :Button = findViewById(R.id.retrofitBtn)
        val volleybtn :Button = findViewById(R.id.VolleyBtn)
        val ktorbtn :Button = findViewById(R.id.ktorBtn)


        retrofitbtn.setOnClickListener()
        {
            val intent = Intent(this, Retrofit::class.java)
            startActivity(intent)
        }


        volleybtn.setOnClickListener()
        {
            val intent = Intent(this, Volley::class.java)
            startActivity(intent)
        }

        jsonParsing.setOnClickListener()
        {
            val intent = Intent(this, JsonParsing::class.java)
            startActivity(intent)
        }

        ktorbtn.setOnClickListener()
        {
            val intent = Intent(this, Ktor::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}