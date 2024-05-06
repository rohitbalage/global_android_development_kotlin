package com.rrbofficial.androiduipracticekotlin

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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Firebase_userdata : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storageRef: StorageReference
    private lateinit var imageViewProfile: ImageView
    private lateinit var editTextSkills: EditText
    private lateinit var editTextDegree: EditText
    private lateinit var buttonUpdateUserData: Button
    private var imageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var buttonSignOut: Button

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

        // Button click listeners
        buttonUpdateUserData.setOnClickListener {
            updateUserData()
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
        }
    }

    private fun updateUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userRef = db.collection("users").document(userId)
            val skills = editTextSkills.text.toString()
            val degree = editTextDegree.text.toString()

            // Update skills and degree in Firestore
            userRef.update(mapOf(
                "skills" to skills,
                "degree" to degree
            ))
                .addOnSuccessListener {
                    Toast.makeText(this, "User data updated successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w("Firebase_userdata", "Error updating document", e)
                }

            // If a new profile picture is selected, upload it to Firebase Storage
            if (imageUri != null) {
                val profileImageRef = storageRef.child("profile_images").child(userId)
                profileImageRef.putFile(imageUri!!)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Profile picture updated successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firebase_userdata", "Error uploading profile picture", e)
                    }
            }
        }
    }
    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
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
