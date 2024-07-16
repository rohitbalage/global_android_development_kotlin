package com.rrbofficial.androiduipracticekotlin.JetpackCompose.NavigationComponent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityNavigationMainBinding

class NavigationMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this,R.layout.activity_navigation_main)
    }
}