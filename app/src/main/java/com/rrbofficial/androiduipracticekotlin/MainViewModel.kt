package com.rrbofficial.androiduipracticekotlin

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _isNightMode = MutableLiveData<Boolean>()
    val isNightMode: LiveData<Boolean> get() = _isNightMode

    init {
        // Initialize with the saved theme preference
        val sharedPreferences = getApplication<Application>().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        _isNightMode.value = sharedPreferences.getBoolean("NightMode", false)
    }

    fun toggleTheme(isNightMode: Boolean) {
        _isNightMode.value = isNightMode
        val sharedPreferences = getApplication<Application>().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("NightMode", isNightMode)
            apply()
        }
    }
}
