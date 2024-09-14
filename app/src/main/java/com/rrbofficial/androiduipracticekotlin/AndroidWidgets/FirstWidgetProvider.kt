package com.rrbofficial.androiduipracticekotlin.AndroidWidgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.widget.RemoteViews
import com.rrbofficial.androiduipracticekotlin.R

class FirstWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            // Create RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.widget_layout_first)

            // Gather device information
            val deviceName = Build.DEVICE
            val modelName = Build.MODEL
            val manufacturer = Build.MANUFACTURER
            val brand = Build.BRAND
            val buildFingerprint = Build.FINGERPRINT
            val sdkVersion = Build.VERSION.SDK_INT.toString()
            val cores = Runtime.getRuntime().availableProcessors()
            val ram = getTotalRAM()
            val storage = getTotalStorage()

            // Battery Info
            val batteryInfo = getBatteryInfo(context)

            // Set gathered information to the TextViews in the widget
            views.setTextViewText(R.id.device_name, "Device Name: $deviceName")
            views.setTextViewText(R.id.model_name, "Model Name: $modelName")
            views.setTextViewText(R.id.manufacturer, "Manufacturer: $manufacturer")
            views.setTextViewText(R.id.brand, "Brand: $brand")
            views.setTextViewText(R.id.build_fingerprint, "Build Fingerprint: $buildFingerprint")
            views.setTextViewText(R.id.sdk_version, "SDK Version: $sdkVersion")
            views.setTextViewText(R.id.cpu_cores, "CPU Cores: $cores")
            views.setTextViewText(R.id.ram, "RAM: $ram")
            views.setTextViewText(R.id.storage, "Storage: $storage")
            views.setTextViewText(R.id.battery_level, "Battery Level: ${batteryInfo.first}%")
            views.setTextViewText(R.id.battery_status, "Battery Status: ${batteryInfo.second}")

            // Push update to the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    // Helper to get total RAM
    private fun getTotalRAM(): String {
        val totalRam = Runtime.getRuntime().totalMemory() / (1024 * 1024) // In MB
        return "$totalRam MB"
    }

    // Helper to get total storage
    private fun getTotalStorage(): String {
        val storage = Environment.getExternalStorageDirectory()
        val totalSpace = storage.totalSpace / (1024 * 1024 * 1024) // In GB
        return "$totalSpace GB"
    }

    // Helper to get battery information
    private fun getBatteryInfo(context: Context): Pair<Int, String> {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val batteryLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        val batteryStatus = "Charging" // Simplified, can add more logic if needed
        return Pair(batteryLevel, batteryStatus)
    }
}
