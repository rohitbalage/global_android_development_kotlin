package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class JetpackCompose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_compose)
    }

    fun goToMVVMandRoom(view: View) {
        val intent = Intent(this, MVMandRoom::class.java)
        startActivity(intent)
    }
    fun goToDependencyInjection(view: View) {
        val intent = Intent(this, DependencyInjection::class.java)
        startActivity(intent)

    }
    fun goToLiveData(view: View) {
        val intent = Intent(this, LiveData::class.java)
        startActivity(intent)

    }
    fun goToDataBinding(view: View) {
        val intent = Intent(this, Databinding::class.java)
        startActivity(intent)

    }
    fun goToViewModel(view: View) {
        val intent = Intent(this, ViewModel::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() {
            val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}