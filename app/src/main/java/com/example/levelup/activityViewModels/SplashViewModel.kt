package com.example.levelup.activityViewModels

import android.app.Application
import androidx.lifecycle.asLiveData
import com.example.levelup.baseClasses.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel(application) {

    private var showOnBoarding: Boolean = true

    val readShowOnBoarding = dataStoreRepository.checkingOnBoardingScreen.asLiveData()
    val isUserLoggedIn = dataStoreRepository.isUserLoggedIn.asLiveData()

}