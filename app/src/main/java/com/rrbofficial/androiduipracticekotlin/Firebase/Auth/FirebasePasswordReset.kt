package com.rrbofficial.androiduipracticekotlin.Firebase.Auth
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityFirebasePasswordResetBinding

class FirebasePasswordReset : AppCompatActivity() {

    private lateinit var binding: ActivityFirebasePasswordResetBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirebasePasswordResetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnResetPassword.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()

            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showResetSuccessDialog(email)
                        } else {
                            showResetFailedDialog()
                        }
                    }
            } else {
                binding.emailEditText.error = "Email is required."
            }
        }
    }

    private fun showResetSuccessDialog(email: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Password Reset Email Sent")
            .setMessage("A password reset email has been sent to $email. Please check your email.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                finish() // Finish the activity after password reset success
            }
            .create()

        alertDialog.show()
    }

    private fun showResetFailedDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Password Reset Failed")
            .setMessage("Failed to send password reset email. Please try again later.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }
}
