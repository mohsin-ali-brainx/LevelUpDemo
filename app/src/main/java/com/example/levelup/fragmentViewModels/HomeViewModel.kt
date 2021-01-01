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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var menuList : List<Menu>
    var quote : MutableLiveData<RandomQuote> = MutableLiveData()
//    var user : MutableLiveData<SignedInUser> = MutableLiveData()

    init {
        menuList = listOf(
                Menu("Do Today",  R.drawable.ic_do_today),
                Menu("Activities & Tips",  R.drawable.ic_tips),
                Menu("Track It",  R.drawable.ic_track),
                Menu("Plan",  R.drawable.ic_plan),
                Menu("Training",  R.drawable.ic_training),
                Menu("Say & Share",  R.drawable.ic_share)
        )
    }

    fun  getMenuList(): List<Menu> {
        return menuList
    }

    val readUserDetails = dataStoreRepository.getUserDetail.asLiveData()

    fun getRandomQuote(header:Map<String,String>){
        showProcessingLoader()
        quote.postValue(RandomQuote("John","Time is everything"))
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
            showToast(error)
        }

    }

}