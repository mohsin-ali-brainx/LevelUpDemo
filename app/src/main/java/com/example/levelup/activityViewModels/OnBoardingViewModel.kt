package com.example.levelup.activityViewModels

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.levelup.R
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.models.OnBoardingModel
import com.example.levelup.utils.LevelUpConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OnBoardingViewModel(application: Application): BaseViewModel(application) {

    private var onBoardingSlides : List<OnBoardingModel> = listOf(
        OnBoardingModel(LevelUpConstants.ONBOARDING_SLIDE_TITLE_1, LevelUpConstants.ONBOARDING_SLIDE_DESC_1, R.drawable.ic_slide_1),
        OnBoardingModel(LevelUpConstants.ONBOARDING_SLIDE_TITLE_1,LevelUpConstants.ONBOARDING_SLIDE_DESC_1,R.drawable.ic_slide_2),
        OnBoardingModel(LevelUpConstants.ONBOARDING_SLIDE_TITLE_1,LevelUpConstants.ONBOARDING_SLIDE_DESC_1,R.drawable.ic_slide_1)
    )

    fun stopOnboardingScreen(){
        viewModelScope.launch(Dispatchers.IO) {
           dataStoreRepository.stopShowingOnboardingScreen()
        }
    }

    fun  getOnBoardingSlides(): List<OnBoardingModel> {
        return onBoardingSlides
    }

}