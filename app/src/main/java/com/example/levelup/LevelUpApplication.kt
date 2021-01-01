package com.example.levelup

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("Registered")
class LevelUpApplication : Application() {

    companion object{
        private lateinit var mApp:LevelUpApplication
        var isInternetConnected = true;
        fun getApplication(): LevelUpApplication = mApp
        fun getTmContext(): Context = mApp.applicationContext
    }

    override fun onCreate() {
        mApp = this
        super.onCreate()
    }

}