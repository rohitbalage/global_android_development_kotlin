package com.rrbofficial.androiduipracticekotlin.MYSQL

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MYSQLLogin : AppCompatActivity() {

    // Declare variables for views
    private lateinit var etUserIdOrEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mysqllogin)

        // Initialize views
        etUserIdOrEmail = findViewById(R.id.etUserIdOrEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val userIdOrEmail = etUserIdOrEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (userIdOrEmail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email ID and password are required", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(userIdOrEmail, password)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        val client = OkHttpClient()

        val formBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url("https://rrbofficial.com/login.php")
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MYSQLLogin, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    val json = JSONObject(responseData)
                    runOnUiThread {
                        if (json.getString("status") == "success") {
                            Toast.makeText(this@MYSQLLogin, "Login successful!", Toast.LENGTH_SHORT).show()
                            // Navigate to the next activity if needed
                        } else {
                            Toast.makeText(this@MYSQLLogin, "Login failed: ${json.getString("message")}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@MYSQLLogin, "Login failed: server error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
