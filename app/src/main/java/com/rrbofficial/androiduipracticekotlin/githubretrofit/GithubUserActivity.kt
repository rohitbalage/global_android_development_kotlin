package com.rrbofficial.androiduipracticekotlin.githubretrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityGithubUserBinding

class GithubUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityGithubUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receiving Intent ****************************************************************************
        val bundle: Bundle? = intent.extras

        val str = bundle?.get("value")

        var txt : TextView = findViewById(R.id.githubUserNameTv)

        txt.text = str.toString()

        Log.d("LOGIN INFO", "UserName: $str")

        //**************************************************************************************************


    }
}