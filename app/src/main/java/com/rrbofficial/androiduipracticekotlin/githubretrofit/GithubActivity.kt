package com.rrbofficial.androiduipracticekotlin.githubretrofit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityGithubBinding

class GithubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGithubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_github)

        // Access your EditText and Button via the binding object
        val loginToGithubButton = binding.loginToGithubBtn
        val githubUsernameEditText = binding.editTextgithubUsername

        loginToGithubButton.setOnClickListener {
            val enteredUsername = githubUsernameEditText.text.toString()
            goToGithubUserActivity(enteredUsername)
        }
    }

    private fun goToGithubUserActivity(username: String) {
        // Explicit Intent
        val i = Intent(this, GithubUserActivity::class.java).apply {
            // Passing data:
            putExtra("value", username)
        }
        startActivity(i)
    }
}
