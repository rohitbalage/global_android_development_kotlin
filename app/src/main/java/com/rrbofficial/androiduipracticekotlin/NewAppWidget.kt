package com.rrbofficial.androiduipracticekotlin

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.rrbofficial.androiduipracticekotlin.AdvancedUIWidgets.AndroidUIWidgets
import com.rrbofficial.androiduipracticekotlin.Firebase.Firebase

class NewAppWidget : AppWidgetProvider() {

    // This method is called to update the widget when necessary (e.g., on creation, refresh, etc.)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Loop through all widget instances (if multiple widgets are placed)
        for (appWidgetId in appWidgetIds) {
            // Set up Intent to launch Activity1
            val intent1 = Intent(context, MainActivity::class.java)
            val pendingIntent1 = PendingIntent.getActivity(
                context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT
            )

            // Set up Intent to launch Activity2
            val intent2 = Intent(context, AndroidUIWidgets::class.java)
            val pendingIntent2 = PendingIntent.getActivity(
                context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT
            )

            // Get the layout for the App Widget and attach on-click listeners to the buttons
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            views.setOnClickPendingIntent(R.id.button1, pendingIntent1)  // Button for Activity1
            views.setOnClickPendingIntent(R.id.button2, pendingIntent2)  // Button for Activity2

            // Tell the AppWidgetManager to update the current widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
