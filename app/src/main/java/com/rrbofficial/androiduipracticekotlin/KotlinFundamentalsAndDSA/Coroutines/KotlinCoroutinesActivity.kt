package com.rrbofficial.androiduipracticekotlin.KotlinFundamentalsAndDSA.Coroutines

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rrbofficial.androiduipracticekotlin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KotlinCoroutinesActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var btnDownloadUserData : Button
    private lateinit var btnCount : Button
    private lateinit var tvCount : TextView
    private lateinit var tvUserMessage : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kotlin_coroutines)
        btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
        btnCount = findViewById(R.id.btnCount)
        tvCount = findViewById(R.id.tvCount)
        tvUserMessage = findViewById(R.id.tvUserMessage)


        CoroutineScope(Dispatchers.IO).launch {
            Log.i("IOThread", "Hello from ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.i("MainThread", "Hello from ${Thread.currentThread().name}")
        }


        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {

            //coroutine scope : is an interface provide scope of coroutine.
            // threads are not equal to coroutines. one thread can have many coroutines.
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }

    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
           // Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")

          // if you call with this : it will  crash show message called with wrong thread exception.
//            tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"

            // we cannot call withContext with out suspend function.
            withContext(Dispatchers.Main){
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }


        }
    }
}