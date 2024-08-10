package com.rrbofficial.androiduipracticekotlin.PaymentIntegration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityPaymentIntegrationBinding
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class PaymentIntegrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentIntegrationBinding
    private lateinit var paymentSheet: PaymentSheet
    private var clientSecret: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up the binding
        binding = ActivityPaymentIntegrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Stripe with your publishable key
        PaymentConfiguration.init(applicationContext, "pk_test_51Ln8pAB0QCAdkJhcprPtmzWQiTU1hD6HmMzf5uL8BTl2eW7cJICwEFrmypaWYHrWF3dH2hHPDV36FTYH4uPtNHJF00J4Hz4Zvr")

        // Initialize PaymentSheet
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        // Set the onClick listener using binding
        binding.payWithStripe.setOnClickListener {
            onPayWithStripeClick()
        }


    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

    // Method to handle button click
    private fun onPayWithStripeClick() {
        createPaymentIntent() // This will create a PaymentIntent and fetch the client secret
    }

    // Make a direct API call to Stripe to create a PaymentIntent
    private fun createPaymentIntent() {
        val url = "https://api.stripe.com/v1/payment_intents"
        val apiKey = "Bearer rk_test_51Ln8pAB0QCAdkJhcEbPyn1pWzcj6p8AKEXSg1DvKGtZjFvJhcb2AziTtU1pMU2P8eH7bMEMy8593o1yKR8ERVRcS00wCVPNRa8"

        val requestBody = FormBody.Builder()
            .add("amount", "100") // Amount in cents, so 100 cents = $1.00
            .add("currency", "usd")
            .build()

        val request = Request.Builder()
            .url(url)
            .header("Authorization", apiKey)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@PaymentIntegrationActivity, "Failed to create Payment Intent", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { body ->
                    try {
                        val json = JSONObject(body)
                        clientSecret = json.getString("client_secret")
                        runOnUiThread {
                            presentPaymentSheet(clientSecret!!)
                        }
                    } catch (e: JSONException) {
                        runOnUiThread {
                            Toast.makeText(
                                this@PaymentIntegrationActivity,
                                "Failed to parse Payment Intent",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        })
    }

    // Present the PaymentSheet to complete the payment
    private fun presentPaymentSheet(clientSecret: String) {
        paymentSheet.presentWithPaymentIntent(clientSecret, PaymentSheet.Configuration("Your Business Name"))
    }

    // Handle the result of the PaymentSheet
    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Completed -> {
                Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show()
            }
            is PaymentSheetResult.Canceled -> {
                Toast.makeText(this, "Payment canceled!", Toast.LENGTH_SHORT).show()
            }
            is PaymentSheetResult.Failed -> {
                Toast.makeText(this, "Payment failed: ${paymentSheetResult.error.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
