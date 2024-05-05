package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class firebase_login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loginBtn: Button
    private lateinit var emailedt: EditText
    private lateinit var passedt: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_login)


        // Initialize Firebase Auth
        auth = com.google.firebase.ktx.Firebase.auth

        loginBtn = findViewById(R.id.btn_loginfirebase)

        emailedt = findViewById(R.id.editTextEmailAddressLogin)
        passedt = findViewById(R.id.editTextPasswordLogin)


        // send this to login
        var email = emailedt.text.toString()
        var password = passedt.text.toString()


        Log.d("Email", email)
        Log.d("Password", email)

        loginBtn.setOnClickListener()
        {
            login(email, password)
        }

    }


    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success, update UI with the signed-in user's information
                    Log.d("login", "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this, firebase_userdata::class.java)
                    startActivity(intent)
                } else {
                    // If login fails, display a message to the user.
                    Log.w("login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
