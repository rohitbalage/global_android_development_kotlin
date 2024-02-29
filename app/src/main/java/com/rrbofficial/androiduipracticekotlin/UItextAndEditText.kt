package com.rrbofficial.androiduipracticekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class UItextAndEditText : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uitext_and_edit_text)

        val et : EditText = findViewById(R.id.EditTextBox)

        // getting input from edittext
        val editTextInput = et.text


        val btn : Button = findViewById(R.id.buttonSetText)

        btn.setOnClickListener()
        {
            // execute this code when the button is clicked
        Toast.makeText(this,
            "Hi! " +editTextInput,
            Toast.LENGTH_LONG)
            .show()

        }

    }
}