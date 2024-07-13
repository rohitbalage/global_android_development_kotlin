package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleViewModel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.JetpackCompose
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivitySimpleViewModelBinding

class SimpleViewModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleViewModelBinding
    private lateinit var viewModel: SimpleViewModel
    private lateinit var viewModelFactory: SimpleViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // this is how you intialize the viewModel
//        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)

        // ViewModelFactory initialization
        viewModelFactory = SimpleViewModelFactory(0)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SimpleViewModel::class.java)

        // Set initial text
        binding.simpleViewModelTextView.text = viewModel.getCurrentCount().toString()

        // Set onClick listener for updating count
        binding.simpleViewModelBtn.setOnClickListener {
            binding.simpleViewModelTextView.text = viewModel.getUpdatedCount().toString()
        }

        // Set initial total
        binding.resultAdditionTextView.text = viewModel.getTotal().toString()

        // Set onClick listener for adding to total
        binding.addMeBtn.setOnClickListener {
            val inputText = binding.SimpleViewModelEditText.text.toString()
            if (inputText.isNotEmpty()) {
                val inputValue = inputText.toInt()
                viewModel.setTotal(inputValue)
                binding.resultAdditionTextView.text = viewModel.getTotal().toString()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, JetpackCompose::class.java)
        startActivity(intent)
        finish()
    }
}
