package com.example.levelup.baseClasses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.levelup.repository.DataStoreRepository
import com.example.levelup.repository.RetrofitRepository

open class BaseViewModel(application:Application):AndroidViewModel(application) {


    val isLoading = MutableLiveData<Boolean>()
    val errorObserver = MutableLiveData<Any?>()

    companion object{
       lateinit var dataStoreRepository : DataStoreRepository
       lateinit var retrofitRepository : RetrofitRepository
    }

    init {
        dataStoreRepository = DataStoreRepository(application)
        retrofitRepository = RetrofitRepository()
    }

    fun showProcessingLoader() = isLoading.postValue(true)

    fun hideProcessingLoader() = isLoading.postValue(false)

    fun showToast(content: Any?=null) = errorObserver.postValue(content)
}