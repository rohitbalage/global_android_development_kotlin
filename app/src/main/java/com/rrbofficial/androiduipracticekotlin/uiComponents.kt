package com.rrbofficial.androiduipracticekotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class uiComponents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_components)

  // Implicit intent
        var implicitBtn : Button = findViewById(R.id.implicitBtn)

        var explicitBtn : Button = findViewById(R.id.explicitBtn)

        val edittext : EditText = findViewById(R.id.implicitIntentText)

        val goTogoogleBtn : Button = findViewById(R.id.googleImplicit)

        val randomBtn: Button = findViewById(R.id.randomNumberBtn)

        val randomTxt : TextView = findViewById(R.id.randomNumber)

        val shareBtn : Button = findViewById(R.id.shareBtn)


        // IMPLICIT INTENT

        implicitBtn.setOnClickListener()
        {
        // Implicit Intent
            val str = edittext.text.toString()
            // Create the Intent object of this class Context() to Second_activity class
            val intent = Intent(applicationContext, implicitIntent::class.java)
            // now by putExtra method put the value in key, value pair key is
            // message_key by this key we will receive the value, and put the string
            intent.putExtra("value", str)
            // start the Intent
            startActivity(intent)
        }


        // EXPLICT  INTENT

        explicitBtn.setOnClickListener()
        {
        // Explicit Intent
            val str = edittext.text.toString()
            var i :Intent = Intent(this,explicitIntent::class.java)
            // Passing data:
            i.putExtra("value",str)
            startActivity(i)

        }

        // GOOGLE IMPLICIT

        goTogoogleBtn.setOnClickListener()
        {
           intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://google.com"))
            startActivity(intent)
        }


        randomBtn.setOnClickListener()
        {
            val random : Int = generateRandomNum()

            randomTxt.text = random.toString()
        }

        shareBtn.setOnClickListener()
        {

            val str = edittext.text.toString()
            val random : Int = generateRandomNum()
            shareData(str,random)
        }

    }

    private fun generateRandomNum(): Int {
       var random =  Random.nextInt(1000)
        return random
    }


    fun shareData(name: String, randomnum: Int) {
        // Implicit intent
        var i = Intent(Intent.ACTION_SEND)
        i.putExtra(Intent.EXTRA_SUBJECT, "$name is luck today!")
        i.putExtra(Intent.EXTRA_TEXT,"His random no is $randomnum")
        startActivity(i)
    }

    fun goToAndroidUIWidgets(view: View) {
        val intent = Intent(this, AndroidUIWidgets::class.java)
        startActivity(intent)
    }
}