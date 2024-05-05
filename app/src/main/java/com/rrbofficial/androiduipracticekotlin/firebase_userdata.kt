package com.rrbofficial.androiduipracticekotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore

class firebase_userdata : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var useremail: TextView
    private lateinit var currentUser: FirebaseUser
    private lateinit var db: FirebaseFirestore

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_userdata)

        // Initialize Firebase Auth
        auth = com.google.firebase.ktx.Firebase.auth
        useremail = findViewById(R.id.useremail_firebaseuser)


        // Set current user's email
        useremail.text = currentUser.email


        // Load user's skills and degree
        loadUserData()
    }

    private fun loadUserData() {
        // Assuming your Firestore document structure is like "users/userId/data"
        val docRef = db.collection("users").document(currentUser.uid)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val skills = document.getString("skills") ?: ""
                    val degree = document.getString("degree") ?: ""

                } else {
                    Log.d("data", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("data", "get failed with ", exception)
//            }
            }
    }
}