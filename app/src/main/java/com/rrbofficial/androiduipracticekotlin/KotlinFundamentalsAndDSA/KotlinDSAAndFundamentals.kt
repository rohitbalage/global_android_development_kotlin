package com.rrbofficial.androiduipracticekotlin.KotlinFundamentalsAndDSA

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.ImplicitIntent
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityKotlindsaFundamentalsBinding
import com.rrbofficial.androiduipracticekotlin.databinding.ActivitySimpleViewModelWithLiveDataBinding

class KotlinDSAAndFundamentals : AppCompatActivity() {

    private lateinit var binding: ActivityKotlindsaFundamentalsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlindsaFundamentalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.kotlinFundamentalsbtn.setOnClickListener()
       {
           val link = "https://docs.google.com/document/d/10bC6nxe2sbsELY1QuDNwNUHUbrtZtm2G/edit?usp=drive_link&ouid=104171225019598136614&rtpof=true&sd=true"
           val intent = Intent(applicationContext, KotlinDocumentActivity::class.java)
           intent.putExtra("value", link)
           startActivity(intent)
       }


    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}