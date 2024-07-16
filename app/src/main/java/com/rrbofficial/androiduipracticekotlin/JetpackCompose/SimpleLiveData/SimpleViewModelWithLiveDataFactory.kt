package com.rrbofficial.androiduipracticekotlin.JetpackCompose.SimpleLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SimpleViewModelWithLiveDataFactory(private val startingTotal : Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimpleViewModelWithLiveDataViewModel::class.java)){
            return SimpleViewModelWithLiveDataViewModel(startingTotal) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}