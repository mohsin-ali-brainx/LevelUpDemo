package com.example.levelup.fragmentViewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.interfaces.IResponse
import com.example.levelup.models.UserSignout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : BaseViewModel(application) {

    var logout : MutableLiveData<Boolean> = MutableLiveData()

    fun signOutUser(header:Map<String,String>){
        showProcessingLoader()
      viewModelScope.launch(Dispatchers.IO) {
          retrofitRepository.signOut(header, logoutListener)
      }
    }

    fun setUserLogout(){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.setLogoutUser()
        }
    }

    private val logoutListener = object : IResponse<UserSignout, String> {
        override fun onSuccess(result: UserSignout) {
            //hideProcessingLoader()
            logout.postValue(true)

        }

        override fun onFailure(error: String) {
           // hideProcessingLoader()
            showToast(error)
        }

    }

}