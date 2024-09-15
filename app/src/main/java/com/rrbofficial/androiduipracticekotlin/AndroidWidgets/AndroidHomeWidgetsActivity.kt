package com.rrbofficial.androiduipracticekotlin.AndroidWidgets

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.rrbofficial.androiduipracticekotlin.R

class AndroidHomeWidgetsActivity : AppCompatActivity() {

    private var sum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_home_widgets)

        val editTextActivity = findViewById<EditText>(R.id.editTextActivity)
        val addButtonActivity = findViewById<Button>(R.id.addButtonActivity)
        val answerTextActivity = findViewById<TextView>(R.id.answerTextActivity)

        addButtonActivity.setOnClickListener {
            val input = editTextActivity.text.toString()
            if (input.isNotEmpty()) {
                val number = input.toInt()
                sum += number

                // Update the activity TextView to show the current sum
                answerTextActivity.text = "Answer: $sum"

                // Send the updated sum to the Wear OS device
                sendDataToWearOS(sum)

                // Update the second widget with the current sum
                updateSecondWidget(applicationContext)
            }
        }
    }

    private fun sendDataToWearOS(answer: Int) {
        // Create a PutDataMapRequest with the path /add_value
        val dataMapRequest = PutDataMapRequest.create("/add_value").apply {
            dataMap.putInt("answer_key", answer)  // Add the sum to the DataMap
        }
        val putDataRequest = dataMapRequest.asPutDataRequest()
        putDataRequest.setUrgent()  // Set the data as urgent for immediate syncing

        // Send the data using Wearable DataClient
        Wearable.getDataClient(this).putDataItem(putDataRequest)
            .addOnSuccessListener {
                Log.d("AndroidHomeWidgets", "Data sent to Wear OS: $answer")
            }
            .addOnFailureListener { e ->
                Log.e("AndroidHomeWidgets", "Failed to send data", e)
            }
    }

    private fun updateSecondWidget(context: Context) {
        val widgetManager = AppWidgetManager.getInstance(context)

        // Update the second widget (only displaying the answer)
        val secondWidget = ComponentName(context, SecondWidgetProvider::class.java)
        val remoteViewsSecondWidget = RemoteViews(context.packageName, R.layout.widget_layout_second)
        remoteViewsSecondWidget.setTextViewText(R.id.answerTextOnlyWidget, "Answer: $sum")

        // Apply the update to the second widget
        widgetManager.updateAppWidget(secondWidget, remoteViewsSecondWidget)
    }
}
