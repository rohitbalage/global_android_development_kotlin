package com.rrbofficial.androiduipracticekotlin.ExternalUILibraries

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityExternUiLibrariesBinding

class ExternalUILibrariesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExternUiLibrariesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_extern_ui_libraries)

        // Setting up click listener using data binding
        binding.tastyToastBtn.setOnClickListener {
            // Show a toast message when the button is clicked
            Toast.makeText(this, "Tasty Toast Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
