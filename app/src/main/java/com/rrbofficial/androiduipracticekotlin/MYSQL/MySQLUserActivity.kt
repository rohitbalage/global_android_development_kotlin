package com.rrbofficial.androiduipracticekotlin.MYSQL

import ApiService
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rrbofficial.androiduipracticekotlin.Databases
import com.rrbofficial.androiduipracticekotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream

class MySQLUserActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var emailEditText: EditText
    private lateinit var hobbyEditText: EditText
    private lateinit var degreeEditText: EditText
    private lateinit var confirmButton: Button
    private lateinit var signOutButton: Button
    private var profileImageBase64: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_sqluser)

        // Initialize views
        profileImageView = findViewById(R.id.imgProfilePicture)
        emailEditText = findViewById(R.id.etEmail)
        hobbyEditText = findViewById(R.id.etHobby)
        degreeEditText = findViewById(R.id.etDegree)
        confirmButton = findViewById(R.id.btnConfirm)
        signOutButton = findViewById(R.id.btnSignOut)

        // Get the user data passed from MYSQLLogin activity
        val email = intent.getStringExtra("email")
        val hobby = intent.getStringExtra("hobby")
        val degree = intent.getStringExtra("degree")
        profileImageBase64 = intent.getStringExtra("profile_picture")

        // Set initial values
        emailEditText.setText(email)
        hobbyEditText.setText(hobby)
        degreeEditText.setText(degree)
        if (profileImageBase64 != null) {
            // Convert Base64 to Bitmap and display in ImageView
            val bitmap = convertBase64ToBitmap(profileImageBase64!!)
            profileImageView.setImageBitmap(bitmap)
        }

        // Handle profile picture change
        findViewById<Button>(R.id.btnChangeProfilePicture).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        // Handle confirm button click to save the data
        confirmButton.setOnClickListener {
            val updatedEmail = emailEditText.text.toString()
            val updatedHobby = hobbyEditText.text.toString()
            val updatedDegree = degreeEditText.text.toString()

            if (updatedEmail.isNotEmpty() && updatedHobby.isNotEmpty() && updatedDegree.isNotEmpty()) {
                // Call the API to save the updated data
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("https://rrbofficial.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                        val apiService = retrofit.create(ApiService::class.java)
                        val response = apiService.updateUserProfile(
                            email = updatedEmail,
                            hobby = updatedHobby,
                            degree = updatedDegree,
                            profile_picture = profileImageBase64 // Optional, only if the user changed the picture
                        )

                        if (response.isSuccessful) {
                            runOnUiThread {
                                Toast.makeText(this@MySQLUserActivity, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this@MySQLUserActivity, "Failed to update profile: ${response.message()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@MySQLUserActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle sign out button click to go back to Databases activity
        signOutButton.setOnClickListener {
            // Redirect to Databases activity
            val intent = Intent(this, Databases::class.java)
            startActivity(intent)
            finish() // Close this activity
        }
    }

    // Handle the image selection result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            profileImageView.setImageURI(imageUri)

            // Convert the selected image to Base64
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            profileImageBase64 = convertBitmapToBase64(bitmap)
        }
    }

    // Helper function to convert a bitmap to Base64 string
    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val imageBytes = outputStream.toByteArray()
        return android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT)
    }

    // Helper function to convert Base64 string to Bitmap
    private fun convertBase64ToBitmap(base64String: String): Bitmap {
        val imageBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}
