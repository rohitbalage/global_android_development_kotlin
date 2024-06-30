package com.rrbofficial.androiduipracticekotlin.Firebase.Auth

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.rrbofficial.androiduipracticekotlin.R

class FirebaseResetEmail : AppCompatActivity() {

    private lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_reset_email)

        etEmail = findViewById(R.id.emailEditText)
        val btnResetEmail = findViewById<Button>(R.id.btnResetEmail)
        btnResetEmail.setOnClickListener {
            val newEmail = etEmail.text.toString().trim()
            if (newEmail.isNotEmpty()) {
                resetEmail(newEmail)
            } else {
                // Handle empty email case if needed
                Log.e("FirebaseResetEmail", "Email field is empty.")
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("Email is empty")
                    .setMessage("Please enter your new email address.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()

                alertDialog.show()
            }
        }
    }

    private fun resetEmail(newEmail: String) {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.updateEmail(newEmail)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseResetEmail", "Email address updated successfully.")
                    val alertDialog = AlertDialog.Builder(this)
                        .setTitle("Email Updated")
                        .setMessage("Your email address has been updated to $newEmail.")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                            finish() // Finish the activity after email update success
                        }
                        .create()

                    alertDialog.show()
                } else {
                    Log.e("FirebaseResetEmail", "Failed to update email address.", task.exception)
                    val alertDialog = AlertDialog.Builder(this)
                        .setTitle("Failed to Update Email")
                        .setMessage("Failed to update your email address. Please try again later.")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()

                    alertDialog.show()
                }
            }
    }
}
