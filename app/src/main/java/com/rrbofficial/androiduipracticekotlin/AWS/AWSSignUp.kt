package com.rrbofficial.androiduipracticekotlin.AWS

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R

class AWSSignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aws_signup)

    }

    override fun onBackPressed() {
        val intent = Intent(this, AWSDBActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

}