package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleViewModel

import androidx.lifecycle.ViewModel

class SimpleViewModel(startingTotal: Int) : ViewModel() {
    private var count = 0
    private var addition = startingTotal

    fun getCurrentCount(): Int {
        return count
    }

    fun getUpdatedCount(): Int {
        count++
        return count
    }

    fun getTotal(): Int {
        return addition
    }

    fun setTotal(input: Int) {
        addition += input
    }
}
