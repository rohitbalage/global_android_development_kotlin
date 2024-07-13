package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleViewModel

import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {
    private var count =0

    fun getCurrentCount(): Int {
        return count
    }

    fun getUpdatedCount(): Int {
        count++
        return count
    }

}