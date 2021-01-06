package com.example.levelup.activityViewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.interfaces.IResponse
import com.example.levelup.models.SignedInUser
import com.example.levelup.models.User
import com.example.levelup.utils.LevelUpConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var showPassword: Boolean = false
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    val logedInUser : MutableLiveData<SignedInUser> = MutableLiveData()


    fun loginUser(){

        if(email.value.isNullOrEmpty() || password.value.isNullOrEmpty() ){
            showErrorDialog(LevelUpConstants.EMPTY_CREDENTAILS)
            return
        }

        if(password.value!!.length < 6 ){
            showErrorDialog(LevelUpConstants.PASSWORD_CREDENTIALS)
            return
        }

        showProcessingLoader()
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(email.value,password.value)
           retrofitRepository.signInUser(user,signInListener)
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
            showErrorDialog(error)
        }

    }





}