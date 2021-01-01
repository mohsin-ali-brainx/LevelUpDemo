package com.example.levelup.activityViewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.interfaces.IResponse
import com.example.levelup.models.SignedInUser
import com.example.levelup.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var showPassword: Boolean = false
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    val logedInUser : MutableLiveData<SignedInUser> = MutableLiveData()


    fun loginUser(){
        showProcessingLoader()
        viewModelScope.launch(Dispatchers.IO) {
            var user : User = User(email.value,password.value)
           retrofitRepository.signInUser("application/json",user,signInListener)
        }

    }

    private val signInListener = object : IResponse<SignedInUser, String> {
        override fun onSuccess(result: SignedInUser) {
            hideProcessingLoader()
            logedInUser.postValue(result)
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.setUserLoggedIn(result)
            }

        }

        override fun onFailure(error: String) {
            hideProcessingLoader()
            showToast(error)
        }

    }


}