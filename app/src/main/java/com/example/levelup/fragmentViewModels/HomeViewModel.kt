package com.example.levelup.fragmentViewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.levelup.R
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.interfaces.IResponse
import com.example.levelup.models.Menu
import com.example.levelup.models.RandomQuote
import com.example.levelup.utils.LevelUpConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var menuList : List<Menu> = listOf(
            Menu(LevelUpConstants.HOME_MENU_ITEM_DO_TODAY,  R.drawable.ic_do_today),
            Menu(LevelUpConstants.HOME_MENU_ITEM_TIPS,  R.drawable.ic_tips),
            Menu(LevelUpConstants.HOME_MENU_ITEM_TRACK_IT,  R.drawable.ic_track),
            Menu(LevelUpConstants.HOME_MENU_ITEM_PLAN,  R.drawable.ic_plan),
            Menu(LevelUpConstants.HOME_MENU_ITEM_TRAINING,  R.drawable.ic_training),
            Menu(LevelUpConstants.HOME_MENU_ITEM_SHARE,  R.drawable.ic_share)
    )
    var quote : MutableLiveData<RandomQuote> = MutableLiveData()

    fun  getMenuList(): List<Menu> {
        return menuList
    }

    val readUserDetails = dataStoreRepository.getUserDetail.asLiveData()

    fun getRandomQuote(header:Map<String,String>){
        showProcessingLoader()
       viewModelScope.launch(Dispatchers.IO) {
           retrofitRepository.randomQuote(header,randomQuoteListener)
       }
    }

    private val randomQuoteListener = object : IResponse<RandomQuote, String> {
        override fun onSuccess(result: RandomQuote) {
            hideProcessingLoader()
            quote.postValue(result)
        }

        override fun onFailure(error: String) {
            hideProcessingLoader()
            showErrorDialog(error)
        }

    }

}