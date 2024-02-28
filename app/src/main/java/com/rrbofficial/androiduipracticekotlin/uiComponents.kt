package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class uiComponents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_components)
    }

    fun GoToTextAndEditText(view: View) {

        val buttonClick = findViewById<Button>(R.id.textviewandedit)
        buttonClick.setOnClickListener {
            val intent = Intent(this, UItextAndEditText::class.java)
            startActivity(intent)

        }
    }
}