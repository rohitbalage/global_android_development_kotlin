package com.rrbofficial.androiduipracticekotlin.SharedPreferences

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivitySharedPreferenceBinding

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferenceBinding
    private val sharedPreferences by lazy {
        getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_preference)

        // Set up click listeners
        binding.saveButton.setOnClickListener {
            saveData()
        }

        binding.loadButton.setOnClickListener {
            loadData()
        }
    }

    private fun saveData() {
        val text = binding.editText.text.toString()
        sharedPreferences.edit().putString("savedText", text).apply()
    }

    private fun loadData() {
        val savedText = sharedPreferences.getString("savedText", "")
        binding.editText.setText(savedText)
    }
}
