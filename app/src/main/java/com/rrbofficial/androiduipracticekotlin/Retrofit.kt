package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rrbofficial.androiduipracticekotlin.githubretrofit.GithubActivity

class Retrofit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
    }

    fun goToGithub(view: View) {
        val intent = Intent(this, GithubActivity::class.java)
        startActivity(intent)
    }
}