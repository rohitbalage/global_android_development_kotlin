package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore

class Firebase_signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var signInButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var goToLogin : Button
    private lateinit var progressBar: ProgressBar
    private lateinit var db: FirebaseFirestore
   private  lateinit var  degreeEditText: EditText
   private lateinit var skillsEditText: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_signup)
        // Initialize Firebase Auth
        auth = com.google.firebase.ktx.Firebase.auth
        db = FirebaseFirestore.getInstance()

        // Assigning buttons from XML
        signInButton = findViewById(R.id.btn_signin)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        goToLogin = findViewById(R.id.btn_login)
        progressBar = findViewById(R.id.progressBar)
        degreeEditText = findViewById(R.id.edtdegreetxt)
        skillsEditText = findViewById(R.id.edtskillstxt)


        progressBar.visibility = View.GONE

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val degree= degreeEditText.text.toString()
            val skills = skillsEditText.text.toString()
            signIn(email, password, degree, skills)
        }
    }

    private fun signIn(email: String, password: String, degree: String, skills: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressBar.visibility = View.VISIBLE
                if (task.isSuccessful) {
                    // User creation success, update UI and navigate to user data page
                    Log.d("useraccount", "createUserWithEmail:success")
                    Toast.makeText(
                        baseContext,
                        "User creation success",
                        Toast.LENGTH_SHORT,
                    ).show()

                    // Firebase Cloud Firestore: Create collection for user degree and skills
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userRef = db.collection("users").document(userId)
                        val userData = hashMapOf(
                            "degree" to degree,
                            "skills" to skills
                        )
                        userRef.set(userData)
                            .addOnSuccessListener {
                                Log.d("useraccount", "User data saved successfully")
                            }
                            .addOnFailureListener { e ->
                                Log.w("userfailure", "Error saving user data", e)
                            }
                    }

                    // Navigate to user data page
//                    val intent = Intent(this, firebase_userdata::class.java)
//                    startActivity(intent)
                    progressBar.visibility = View.GONE

                } else {
                    // User creation failed, display a message to the user.
                    Log.w("userfailure", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "User creation failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun signOut() {
        auth.signOut()
        Toast.makeText(
            baseContext,
            "You are sign out",
            Toast.LENGTH_SHORT,
        ).show()

    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // User is signed in
            signInButton.isEnabled = false

        } else {
            // User is signed out
            signInButton.isEnabled = true

        }
    }
}
