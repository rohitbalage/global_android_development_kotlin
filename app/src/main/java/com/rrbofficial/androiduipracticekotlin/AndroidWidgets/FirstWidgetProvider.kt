package com.rrbofficial.androiduipracticekotlin.AndroidWidgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.rrbofficial.androiduipracticekotlin.R

class FirstWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout_first)
            // Set default values to the RemoteViews if needed
            views.setTextViewText(R.id.answertextwidgets, "Answer: 0")
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
