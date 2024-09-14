package com.rrbofficial.androiduipracticekotlin.AndroidWidgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.rrbofficial.androiduipracticekotlin.R

class SecondWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout_second)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
