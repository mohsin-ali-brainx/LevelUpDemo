package com.example.levelup.activityViewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.interfaces.IResponse
import com.example.levelup.models.UpdatePassword
import com.example.levelup.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdatePasswordViewModel (application: Application) : BaseViewModel(application){
    var password = MutableLiveData<String>()
    var confirm_password = MutableLiveData<String>()
    var passwordUpdated = MutableLiveData<Boolean>()

    fun updateUserPassword(header:Map<String,String>){

        val valid =  password.value.equals(confirm_password.value)

        if(!password.value.equals(confirm_password.value)){
            showToast("Your password and confirm password does not matches")
            return
        }

        if (password.value?.isEmpty() == true || confirm_password.value?.isEmpty()==true){
            showToast("Please provide your credentials")
            return
        }
        else{
            showProcessingLoader()
            val user: User = User(null,password.value!!,confirm_password.value!!)
            viewModelScope.launch(Dispatchers.IO) {
                retrofitRepository.updatePassword(header,user,updatePasswordListener)
            }
        }

    }

    private val updatePasswordListener = object : IResponse<UpdatePassword, String> {
        override fun onSuccess(result: UpdatePassword) {
            hideProcessingLoader()
            if (result.message.isNullOrEmpty())
                passwordUpdated.postValue(false)
            else
            passwordUpdated.postValue(true)
        }

        override fun onFailure(error: String) {
            hideProcessingLoader()
            showToast(error)
        }

    }

}