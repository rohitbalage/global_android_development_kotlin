package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class Fragments : AppCompatActivity() {


    lateinit var  replace : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragments)


        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        val firstFragment = Fragment1()

        fragmentTransaction.add(R.id.frame, firstFragment)
        fragmentTransaction.commit()

       replace = findViewById(R.id.buttonReplace)

        replace.setOnClickListener {
            val secondfragmentManager: FragmentManager = supportFragmentManager
            val secondfragmentTransaction: FragmentTransaction = secondfragmentManager.beginTransaction()

            val secondFragment = Fragment2()
            secondfragmentTransaction.replace(R.id.frame, secondFragment) // Use secondfragmentTransaction here

            // Add the second fragment to the back stack
            secondfragmentTransaction.addToBackStack(null)

            secondfragmentTransaction.commit()
        }

    }

}