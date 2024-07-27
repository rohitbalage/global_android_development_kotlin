package com.rrbofficial.androidpracticeautomobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androidpracticeautomobile.databinding.ActivityMainAutomobileBinding

class MainActivityAutomobile : AppCompatActivity() {
    private lateinit var binding: ActivityMainAutomobileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_automobile)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_automobile)
    }
}