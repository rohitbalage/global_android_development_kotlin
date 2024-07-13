package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleViewModel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivitySimpleViewModelBinding

class SimpleViewModelActivity : AppCompatActivity() {

   private  lateinit var  binding: ActivitySimpleViewModelBinding
   private  lateinit var viewModel: SimpleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySimpleViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)

        // set initial text
        binding.simpleViewModelTextView.text = viewModel.getCurrentCount().toString()

        // set onclick listener
        binding.simpleViewModelBtn.setOnClickListener {
            binding.simpleViewModelTextView.text = viewModel.getUpdatedCount().toString()
        }
    }
}