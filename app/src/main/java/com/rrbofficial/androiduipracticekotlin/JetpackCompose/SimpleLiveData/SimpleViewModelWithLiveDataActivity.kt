package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleLiveData

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.JetpackCompose
import com.rrbofficial.androiduipracticekotlin.databinding.ActivitySimpleViewModelWithLiveDataBinding

class SimpleViewModelWithLiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleViewModelWithLiveDataBinding
    private lateinit var viewModel: SimpleViewModelWithLiveDataViewModel
    private lateinit var viewModelFactory: SimpleViewModelWithLiveDataFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySimpleViewModelWithLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = SimpleViewModelWithLiveDataFactory(100)
        viewModel = ViewModelProvider(this,viewModelFactory).get(SimpleViewModelWithLiveDataViewModel::class.java)

        viewModel.totalData.observe(this, Observer {
            binding.resultTextView.text = it.toString()
        })

        binding.insertButton.setOnClickListener {
            viewModel.setTotal(binding.inputEditText.text.toString().toInt())

        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, JetpackCompose::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }
}