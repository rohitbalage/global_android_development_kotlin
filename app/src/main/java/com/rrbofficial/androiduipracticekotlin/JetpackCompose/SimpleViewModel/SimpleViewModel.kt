package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {
    private var count =0
    private var addition = 0


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

    fun setTotal(input :Int){
        addition+=input
    }

}