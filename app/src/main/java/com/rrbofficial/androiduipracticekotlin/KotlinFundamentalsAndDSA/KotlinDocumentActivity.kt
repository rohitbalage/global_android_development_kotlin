package com.rrbofficial.androiduipracticekotlin.KotlinFundamentalsAndDSA

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rrbofficial.androiduipracticekotlin.ImplicitIntent
import com.rrbofficial.androiduipracticekotlin.R

class KotlinDocumentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kotlin_document)


        val intent = intent
        val link = intent.getStringExtra("value")

        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Load the PowerPoint presentation
        val pptUrl = link
        link?.let { webView.loadUrl(it) }
    }
}