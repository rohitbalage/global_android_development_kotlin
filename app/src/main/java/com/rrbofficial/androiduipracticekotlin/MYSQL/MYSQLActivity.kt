package com.rrbofficial.androiduipracticekotlin.MYSQL

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityMysqlactivityBinding

class MYSQLActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMysqlactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using data binding
        binding = ActivityMysqlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup button click listeners
        binding.mysqlsingupbtn.setOnClickListener {
            val intent = Intent(this, MYSQLSignUp::class.java)
            startActivity(intent)
        }

        binding.mysqlloginbtn.setOnClickListener {
            // Handle login button click here
        }

        binding.mysqlpasswordresetbtn.setOnClickListener {
            // Handle password reset click
        }

        binding.mysqlpushnotificationbtn.setOnClickListener {
            // Handle push notification trigger
        }
    }
}
