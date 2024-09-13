package com.rrbofficial.androiduipracticekotlin.MYSQL

data class LoginResponse(
    val status: String,
    val message: String?,
    val email: String?,
    val hobby: String?,
    val degree: String?,
    val profile_picture: String?
)
