package com.rrbofficial.androiduipracticekotlin.PaymentIntegration.RazorPay

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.razorpay.Checkout
import com.rrbofficial.androiduipracticekotlin.R

class RazorPayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_razor_pay)
        /*
       * To ensure faster loading of the Checkout form,
       * call this method as early as possible in your checkout flow
       * */
        Checkout.preload(applicationContext)
        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        co.setKeyID("rzp_live_XXXXXXXXXXXXXX")
    }
}