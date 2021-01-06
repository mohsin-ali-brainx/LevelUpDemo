package com.example.levelup.activityViewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.interfaces.IResponse
import com.example.levelup.models.UpdatePassword
import com.example.levelup.models.User
import com.example.levelup.utils.LevelUpConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdatePasswordViewModel (application: Application) : BaseViewModel(application){
    var password = MutableLiveData<String>()
    var confirmPassword = MutableLiveData<String>()
    var passwordUpdated = MutableLiveData<Boolean>()

    fun updateUserPassword(header:Map<String,String>){

        if (password.value?.isEmpty() == true || confirmPassword.value?.isEmpty()==true){
            showErrorDialog(LevelUpConstants.EMPTY_CREDENTAILS)
            return
        }

        if(!password.value.equals(confirmPassword.value)){
            showErrorDialog(LevelUpConstants.NO_PASS_MATCH)
            return
        }

            showProcessingLoader()
            val user = User(null,password.value!!,confirmPassword.value!!)
            viewModelScope.launch(Dispatchers.IO) {
                retrofitRepository.updatePassword(header,user,updatePasswordListener)
            }
    }

    private val updatePasswordListener = object : IResponse<UpdatePassword, String> {
        override fun onSuccess(result: UpdatePassword) {
            hideProcessingLoader()
            if (result.message.isNullOrEmpty()){
                showErrorDialog(LevelUpConstants.PASS_NOT_UPDATED)
                passwordUpdated.postValue(false)
            }
            else
            passwordUpdated.postValue(true)
        }

        override fun onFailure(error: String) {
            hideProcessingLoader()
            showErrorDialog(error)
        }

    }

}