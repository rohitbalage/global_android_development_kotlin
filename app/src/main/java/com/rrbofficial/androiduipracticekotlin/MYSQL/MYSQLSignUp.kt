package com.rrbofficial.androiduipracticekotlin.MYSQL

import ApiService
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rrbofficial.androiduipracticekotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream

class MYSQLSignUp : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var selectImageButton: Button
    private var profileImageBase64: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mysqlsign_up)

        // Initialize views
        val usernameEditText = findViewById<EditText>(R.id.etUsername)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val hobbyEditText = findViewById<EditText>(R.id.etHobby)
        val degreeEditText = findViewById<EditText>(R.id.etDegree)
        profileImageView = findViewById(R.id.imgProfilePicture)
        selectImageButton = findViewById(R.id.btnSelectImage)

        // Set up the image selection button
        selectImageButton.setOnClickListener {
            // Open gallery to select an image
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        // Handle sign-up button click
        findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()
            val hobby = hobbyEditText.text.toString()
            val degree = degreeEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && profileImageBase64 != null) {
                // Call the API to sign up
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("http://rrbofficial.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                        val apiService = retrofit.create(ApiService::class.java)
                        val response = apiService.signUp(
                            username = username,
                            password = password,
                            email = email,
                            hobby = hobby,
                            degree = degree,
                            profile_picture = profileImageBase64!! // Use the Base64-encoded image
                        )

                        if (response.isSuccessful) {
                            runOnUiThread {
                                Toast.makeText(this@MYSQLSignUp, "Sign-up successful", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this@MYSQLSignUp, "Sign-up failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@MYSQLSignUp, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all fields and select a profile picture", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Handle the image selection result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            profileImageView.setImageURI(imageUri)

            // Convert the selected image to a Base64 string
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

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}
