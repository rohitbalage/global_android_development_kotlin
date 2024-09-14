package com.rrbofficial.androiduipracticekotlin.AndroidWidgets

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

                // Update the activity TextView
                answerTextActivity.text = "Answer: $sum"

                // Update both widgets
                updateWidget(applicationContext)
            }
        }
    }

    private fun updateWidget(context: Context) {
        val widgetManager = AppWidgetManager.getInstance(context)

        // Update the first widget (with EditText and Button)
        val firstWidget = ComponentName(context, FirstWidgetProvider::class.java)
        val remoteViewsFirstWidget = RemoteViews(context.packageName, R.layout.widget_layout_first)
        remoteViewsFirstWidget.setTextViewText(R.id.answertextwidgets, "Answer: $sum")
        widgetManager.updateAppWidget(firstWidget, remoteViewsFirstWidget)

        // Update the second widget (only displaying the answer)
        val secondWidget = ComponentName(context, SecondWidgetProvider::class.java)
        val remoteViewsSecondWidget = RemoteViews(context.packageName, R.layout.widget_layout_second)
        remoteViewsSecondWidget.setTextViewText(R.id.answerTextOnlyWidget, "Answer: $sum")
        widgetManager.updateAppWidget(secondWidget, remoteViewsSecondWidget)
    }
}
