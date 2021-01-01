package com.example.levelup.utils

import androidx.datastore.preferences.core.preferencesKey

class LevelUpConstants{
    companion object{
        const val DATASTORE_PREFERENCE_NAME =  "levelUp_preference"
        val SHOW_ONBOARDING_KEY = preferencesKey<Boolean>("SHOW_ONBOARDING")
        val USER_LOGEDIN_KEY = preferencesKey<Boolean>("USER_LOGEDIN")
        val ACCESS_TOKEN_KEY = preferencesKey<String>("ACCESS_TOKEN")
        val UID_KEY = preferencesKey<String>("UID")
        val CLIENT_KEY = preferencesKey<String>("CLIENT")
        val USERNAME_KEY = preferencesKey<String>("USERNAME")
        const val UPDATEPASSWORD_DIALOG_TAG = "UpdateDialog"
        const val LOGOUT_DIALOG_TAG = "LgoutDialog"
        const val USERNAME = "username"
        const val CLIENT = "client"
        const val UID = "uid"
        const val ACCESS_TOKEN = "access-token"
        const val PASSWORD = "password"
        const val CONFIRM_PASSWORD = "confirm-password"
    }

}