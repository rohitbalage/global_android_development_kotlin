package com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithPhoneNumber

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.rrbofficial.androiduipracticekotlin.R
import java.util.concurrent.TimeUnit

class SignInwithPhoneNumberFirebase : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var phoneNumberEditText: EditText
    private lateinit var otpEditText: EditText
    private lateinit var sendOtpButton: Button
    private lateinit var verifyOtpButton: Button
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_inwith_phone_number_firebase)

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()

        // Initialize views
        phoneNumberEditText = findViewById(R.id.phone_number)
        otpEditText = findViewById(R.id.otp_input)
        sendOtpButton = findViewById(R.id.send_otp_button)
        verifyOtpButton = findViewById(R.id.verify_otp_button)

        // Set the click listener for the "Send OTP" button
        sendOtpButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString().trim()
            if (phoneNumber.isNotEmpty()) {
                sendVerificationCode(phoneNumber)
            } else {
                Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show()
            }
        }

        // Set the click listener for the "Verify OTP" button
        verifyOtpButton.setOnClickListener {
            val otp = otpEditText.text.toString().trim()
            if (otp.isNotEmpty()) {
                verifyCode(otp)
            } else {
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to send the OTP to the provided phone number
    private fun sendVerificationCode(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout duration
            .setActivity(this) // Activity for callback binding
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    // Automatic OTP verification is completed
                    val code = phoneAuthCredential.smsCode
                    code?.let {
                        otpEditText.setText(it)
                        verifyCode(it) // Auto-verify the OTP
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // Verification failed
                    Toast.makeText(this@SignInwithPhoneNumberFirebase, "Verification failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("PhoneAuth", "Verification failed", e)
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    // Code successfully sent to the phone number
                    this@SignInwithPhoneNumberFirebase.verificationId = verificationId
                    resendToken = token

                    // Show OTP input and verify button
                    otpEditText.visibility = View.VISIBLE
                    verifyOtpButton.visibility = View.VISIBLE

                    Toast.makeText(this@SignInwithPhoneNumberFirebase, "OTP sent to $phoneNumber", Toast.LENGTH_SHORT).show()
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // Function to verify the OTP code
    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithCredential(credential)
    }

    // Function to sign in with the PhoneAuthCredential
    private fun signInWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Verification successful!", Toast.LENGTH_SHORT).show()
                    // Navigate to the next screen or do whatever after successful verification
                } else {
                    Toast.makeText(this, "Verification failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
