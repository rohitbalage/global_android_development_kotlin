package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleViewModel

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.rrbofficial.androiduipracticekotlin.JetpackCompose.JetpackCompose
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

        // initialize viewmodel
        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)

        // set initial text
        binding.simpleViewModelTextView.text = viewModel.getCurrentCount().toString()

        // set onclick listener
        binding.simpleViewModelBtn.setOnClickListener {
            binding.simpleViewModelTextView.text = viewModel.getUpdatedCount().toString()
        }

        /*********************FOR ADDITION OF CURRENT TOTAL***************/

        binding.resultAdditionTextView.text = viewModel.getTotal().toString()

        binding.addMeBtn.setOnClickListener {
        viewModel.setTotal(binding.SimpleViewModelEditText.text.toString().toInt())
            binding.resultAdditionTextView.text = viewModel.getTotal().toString()
        }

//        // send converted edittext to viewmodel
//        editText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                val intValue = s?.toString()?.toIntOrNull()
//                viewModel.editTextValue.value = intValue
//            }
//             })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, JetpackCompose::class.java)
        startActivity(intent)
        finish()
    }
}