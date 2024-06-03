package com.rrbofficial.androiduipracticekotlin.Notifications

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.rrbofficial.androiduipracticekotlin.MainActivity
import com.rrbofficial.androiduipracticekotlin.Notifications.util.NotificationHelper
import com.rrbofficial.androiduipracticekotlin.R
import com.rrbofficial.androiduipracticekotlin.databinding.ActivityNotificationsBinding

class Notifications : AppCompatActivity() {

    private var _binding: ActivityNotificationsBinding? = null


    // binding to binding ---------------------------------------------
   private  val binding :ActivityNotificationsBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonClickListener()

    }
    private fun buttonClickListener()
    {


    binding.btndefaultNotification.setOnClickListener()
    {
        val title = binding.txtTitle.text.toString()
        val msg = binding.txtMessage.text.toString()
        NotificationHelper.defaultNotification(this,title,msg)
    }

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}