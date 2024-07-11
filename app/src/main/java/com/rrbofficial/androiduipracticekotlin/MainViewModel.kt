package com.rrbofficial.androiduipracticekotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("MyAppPreferences", Application.MODE_PRIVATE)
    val isNightMode = MutableLiveData<Boolean>()

    init {
        isNightMode.value = sharedPreferences.getBoolean("NightMode", false)
    }

    fun toggleTheme(isChecked: Boolean) {
        viewModelScope.launch {
            sharedPreferences.edit().putBoolean("NightMode", isChecked).apply()
            isNightMode.value = isChecked
        }
    }
}
