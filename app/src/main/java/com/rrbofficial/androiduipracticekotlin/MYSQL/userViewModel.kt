package com.rrbofficial.androiduipracticekotlin.MYSQL

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _signupResult = MutableLiveData<ApiResponse>()
    val signupResult: LiveData<ApiResponse> get() = _signupResult

    fun signUp(username: String, password: String, email: String, hobby: String, degree: String, profilePicture: String) {
        // Use viewModelScope to launch the coroutine
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Make the API call
                val response: Response<ApiResponse> = RetrofitClient.apiService.signUp(username, password, email, hobby, degree, profilePicture)

                // Check if the response is successful
                if (response.isSuccessful && response.body() != null) {
                    _signupResult.postValue(response.body())
                } else {
                    _signupResult.postValue(ApiResponse("error", "Failed to sign up: ${response.message()}"))
                }
            } catch (e: Exception) {
                _signupResult.postValue(ApiResponse("error", "Network request failed: ${e.message}"))
            }
        }
    }
}
