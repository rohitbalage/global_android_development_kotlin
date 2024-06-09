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

        // default notification
    binding.btndefaultNotification.setOnClickListener()
    {
        val title = binding.txtTitle.text.toString()
        val msg = binding.txtMessage.text.toString()
        NotificationHelper.defaultNotification(this,title,msg)
    }
        // high notification
        binding.btnHighNotificaiton.setOnClickListener()
        {
            val title = binding.txtTitle.text.toString()
            val msg = binding.txtMessage.text.toString()
            NotificationHelper.highNotification(this,title,msg)
        }

        // low notification
        binding.btnLowNotificaiton.setOnClickListener()
        {
            val title = binding.txtTitle.text.toString()
            val msg = binding.txtMessage.text.toString()
            NotificationHelper.lowNotification(this,title,msg)
        }

        // action notification
        binding.btnActionNotificaiton.setOnClickListener()
        {
            val title = binding.txtTitle.text.toString()
            val msg = binding.txtMessage.text.toString()
            NotificationHelper.actionNotification(this,title,msg)
        }

        // content  intent notification
        binding.btnContentIntenNotificaiton.setOnClickListener()
        {
            val title = binding.txtTitle.text.toString()
            val msg = binding.txtMessage.text.toString()
            NotificationHelper.contentIntentNotification(this,title,msg)
        }

        // onGoing intent Notification
        binding.btnOnGoingNotificaiton.setOnClickListener()
        {
            val title = binding.txtTitle.text.toString()
            val msg = binding.txtMessage.text.toString()
            NotificationHelper.onGoingNotification(this,title,msg)
        }


        // custom sound Notification
        binding.btncustomSoundNotificaiton.setOnClickListener()
        {
            val title = binding.txtTitle.text.toString()
            val msg = binding.txtMessage.text.toString()
            NotificationHelper.customSoundNotification(this,title,msg)
        }


        // custom sound Notification
        binding.btnbigTextStyleNotificaiton.setOnClickListener()
        {
            val title = binding.txtTitle.text.toString()
            val msg = binding.txtMessage.text.toString()
            NotificationHelper.bigTextStyleNotification(this,title,msg)
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