package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleLiveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModelWithLiveDataViewModel(startingTotal: Int) : ViewModel() {

    // making total as public is not a good practice so we make it private and access it through totalData live data
  private  var total = MutableLiveData<Int>()
    val totalData: LiveData<Int>
        get() = total

    init {
        total.value = startingTotal
    }

    fun setTotal(input:Int){
        total.value = (total.value)?.plus(input)
    }
}