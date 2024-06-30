package com.rrbofficial.androiduipracticekotlin.Firebase.Auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.FacebookSignIn.FacebookSingInFirebase
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.GoogleSignIn.GoogleSignInFirebase
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithEmailAndPassword.Firebase_signup
import com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithPhoneNumber.SignInwithPhoneNumberFirebase
import com.rrbofficial.androiduipracticekotlin.Firebase.CloudMessaging.CloudMessagingFirebaseActivity
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityFirebaseAuthenticationOptionsBinding

class FirebaseAuthenticationOptions : AppCompatActivity() {

    private lateinit var binding: ActivityFirebaseAuthenticationOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_authentication_options)

        // Setup other views and listeners as needed
        setupListeners()
    }

    private fun setupListeners() {
        // Add onClickListeners or other setup as per your requirements
        binding.withEmailIdandPassFirebaseAuth.setOnClickListener {
            val intent = Intent(this, Firebase_signup::class.java)
            startActivity(intent)
        }

        binding.withPhoneNumberFirebaseAuth.setOnClickListener {
            val intent = Intent(this, SignInwithPhoneNumberFirebase::class.java)
            startActivity(intent)
        }

        binding.withGoogleSignInFirebaseAuth.setOnClickListener {
            val intent = Intent(this,GoogleSignInFirebase::class.java)
            startActivity(intent)
        }

        binding.withFbSignInFirebaseAuth.setOnClickListener {
            val intent = Intent(this, FacebookSingInFirebase::class.java)
            startActivity(intent)
        }
    }
}
