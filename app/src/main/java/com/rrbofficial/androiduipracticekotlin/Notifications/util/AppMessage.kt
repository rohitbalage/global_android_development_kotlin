package com.rrbofficial.androiduipracticekotlin.Notifications.util

import androidx.core.app.Person

data class AppMessage(
    val text: String?,
    val timestamp:Long?,
    val person: Person?
)
