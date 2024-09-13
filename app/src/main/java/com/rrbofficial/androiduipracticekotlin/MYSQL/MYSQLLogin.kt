package com.rrbofficial.androiduipracticekotlin.MYSQL

import ApiService
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rrbofficial.androiduipracticekotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MYSQLLogin : AppCompatActivity() {

    private lateinit var userIdOrEmailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mysqllogin)

        // Initialize views
        userIdOrEmailEditText = findViewById(R.id.etUserIdOrEmail)
        passwordEditText = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)

        // Handle login button click
        loginButton.setOnClickListener {
            val userIdOrEmail = userIdOrEmailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (userIdOrEmail.isNotEmpty() && password.isNotEmpty()) {
                // Call the login API
                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("https://rrbofficial.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

                        val apiService = retrofit.create(ApiService::class.java)
                        val response = apiService.login(userIdOrEmail, password)

                        if (response.isSuccessful && response.body() != null) {
                            val loginResponse = response.body()

                            // Log the response for debugging
                            Log.d("MYSQLLogin", "Login Response: ${loginResponse}")

                            // Navigate to MySQLUserActivity after successful login
                            if (loginResponse?.status == "success") {
                                val intent = Intent(this@MYSQLLogin, MySQLUserActivity::class.java)
                                intent.putExtra("email", loginResponse.email)
                                intent.putExtra("hobby", loginResponse.hobby)
                                intent.putExtra("degree", loginResponse.degree)
                                intent.putExtra("profile_picture", loginResponse.profile_picture)
                                startActivity(intent)
                                finish() // Close login activity
                            } else {
                                runOnUiThread {
                                    Toast.makeText(this@MYSQLLogin, "Login failed: ${loginResponse?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this@MYSQLLogin, "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@MYSQLLogin, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
