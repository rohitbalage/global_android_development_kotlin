package com.rrbofficial.androiduipracticekotlin.MachineLearningAndroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityMachineLearningBinding

class MachineLearning : AppCompatActivity() {

    private lateinit var binding: ActivityMachineLearningBinding // Declare binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_machine_learning)

     binding.texttospeechandviceversabtn.setOnClickListener {
         val intent = Intent(this, TextToSpeechSpeechToTextActivity::class.java)
         startActivity(intent)
     }

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}
