package com.rrbofficial.androiduipracticekotlin.Firebase.Auth.WithEmailAndPassword

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.R

class Firebase_userdata : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storageRef: StorageReference
    private lateinit var imageViewProfile: ImageView
    private lateinit var editTextSkills: EditText
    private lateinit var editTextDegree: EditText
    private lateinit var buttonUpdateUserData: Button
    private lateinit var buttonSignOut: Button
    private lateinit var buttonUpdateuserEmail: EditText
    private lateinit var buttonUpdateEmail: Button
    private val PICK_IMAGE_REQUEST = 1 // Define PICK_IMAGE_REQUEST here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_userdata)

        auth = com.google.firebase.ktx.Firebase.auth
        db = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().reference

        imageViewProfile = findViewById(R.id.imageViewProfile)
        editTextSkills = findViewById(R.id.editTextSkills)
        editTextDegree = findViewById(R.id.editTextEducation)
        buttonUpdateUserData = findViewById(R.id.buttonUpdateUserData)
        buttonSignOut = findViewById(R.id.buttonSignOut)
        buttonUpdateuserEmail = findViewById(R.id.buttonUpdateuserEmail)
        buttonUpdateEmail = findViewById(R.id.buttonUpdateEmail)

        // Load user data
        loadUserData()

        // change profile picture
        imageViewProfile.setOnClickListener {
            openImageChooser()
        }

        // Sign out button
        buttonSignOut.setOnClickListener {
            signOut()
        }

        // Update email button click listener
        buttonUpdateEmail.setOnClickListener {
            val newEmail = buttonUpdateuserEmail.text.toString().trim()
            if (newEmail.isNotEmpty()) {
                updateEmail(newEmail)
            } else {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userRef = db.collection("users").document(userId)
            userRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val userData = document.data
                        val profileImageUrl = userData?.get("profileImageUrl").toString()
                        val skills = userData?.get("skills").toString()
                        val degree = userData?.get("degree").toString()

                        // Load profile picture using Glide library
                        Glide.with(this)
                            .load(profileImageUrl)
                            .placeholder(R.drawable.profilepic)
                            .into(imageViewProfile)

                        // Display skills and degree
                        editTextSkills.setText(skills)
                        editTextDegree.setText(degree)
                    } else {
                        Log.d("Firebase_userdata", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Firebase_userdata", "get failed with ", exception)
                }

            // Pre-fill current email address
            val currentUser = auth.currentUser
            buttonUpdateuserEmail.setText(currentUser?.email)
        }
    }

    private fun updateEmail(newEmail: String) {
        val currentUser = auth.currentUser
        currentUser?.updateEmail(newEmail)
            ?.addOnSuccessListener {
                Toast.makeText(this, "Email updated successfully", Toast.LENGTH_SHORT).show()
            }
            ?.addOnFailureListener { e ->
                Log.e("Firebase_userdata", "Error updating email", e)
                Toast.makeText(this, "Failed to update email: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            Glide.with(this).load(imageUri).into(imageViewProfile)
        }
    }

    private fun signOut() {
        auth.signOut()
        // Redirect to login activity after sign-out
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the current activity to prevent back navigation
    }
}
