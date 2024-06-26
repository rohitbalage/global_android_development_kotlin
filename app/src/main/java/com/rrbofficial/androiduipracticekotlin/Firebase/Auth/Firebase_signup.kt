package com.rrbofficial.androiduipracticekotlin.Firebase.Auth

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.rrbofficial.androiduipracticekotlin.R

class Firebase_signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var signInButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var goToLogin: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var db: FirebaseFirestore
    private lateinit var degreeEditText: EditText
    private lateinit var skillsEditText: EditText
    private lateinit var imageViewProfile: ImageView
    private lateinit var buttonSelectPicture: Button
    private var imageUri: Uri? = null

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }


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
        imageViewProfile = findViewById(R.id.imageViewProfile)
        buttonSelectPicture = findViewById(R.id.buttonSelectPicture)
        progressBar.visibility = View.GONE

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val degree = degreeEditText.text.toString()
            val skills = skillsEditText.text.toString()
            signIn(email, password, degree, skills, imageUri)
        }

        buttonSelectPicture.setOnClickListener()
        {
            openImageChooser()
        }

        goToLogin.setOnClickListener()
        {
            val intent = Intent(this, Firebase_login::class.java)
            startActivity(intent)
        }

    }

    private fun signIn(email: String, password: String, degree: String, skills: String, imageUri: Uri?) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressBar.visibility = View.VISIBLE
                if (task.isSuccessful) {
                    // User creation success
                    Log.d("useraccount", "createUserWithEmailAndPassword:success")
                    Toast.makeText(
                        baseContext,
                        "User creation success",
                        Toast.LENGTH_SHORT
                    ).show()

                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid

                        // Check if an image is selected
                        if (imageUri != null) {
                            // Upload profile picture to Firebase Storage
                            val storageRef = FirebaseStorage.getInstance().reference
                                .child("profile_images/$userId/${imageUri.lastPathSegment}")

                            val uploadTask = storageRef.putFile(imageUri)
                            uploadTask.addOnSuccessListener { _ ->
                                // Profile picture uploaded successfully, get the download URL
                                storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                                    // Save user data to Firestore including profile picture URL
                                    val userRef = db.collection("users").document(userId)
                                    val userData = hashMapOf(
                                        "email" to email,
                                        "degree" to degree,
                                        "skills" to skills,
                                        "profileImageUrl" to downloadUri.toString()
                                    )
                                    userRef.set(userData)
                                        .addOnSuccessListener {
                                            Log.d("useraccount", "User data saved successfully")
                                            // Navigate to Firebase_userdata activity
                                            val intent = Intent(this, Firebase_userdata::class.java)
                                            startActivity(intent)
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w("userfailure", "Error saving user data", e)
                                        }
                                }
                            }
                            uploadTask.addOnFailureListener { e ->
                                // Handle failure to upload profile picture
                                Log.w("userfailure", "Error uploading profile picture", e)
                            }
                        } else {
                            // No image selected, save user data without profile picture URL
                            val userRef = db.collection("users").document(userId)
                            val userData = hashMapOf(
                                "email" to email,
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
                    }

                    // Navigate to user data page
                    progressBar.visibility = View.GONE

                } else {
                    // User creation failed, display a message to the user.
                    Log.w("userfailure", "createUserWithEmailAndPassword:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "User creation failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
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

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            imageViewProfile.setImageURI(imageUri)
        }
    }



}
