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
        const val ERROR_DIALOG_TAG = "ErrorDialog"
        const val LOGOUT_DIALOG_TAG = "LogoutDialog"
        const val USERNAME = "username"
        const val CLIENT = "client"
        const val UID = "uid"
        const val ACCESS_TOKEN = "access-token"
        const val PASSWORD = "password"
        const val CONFIRM_PASSWORD = "confirm-password"
        const val CONTENT_TYPE= "application/json"

        const val BOTTOM_NAV_ITEM_HOME= "Home"
        const val BOTTOM_NAV_ITEM_NOTIFICATION= "Notification"
        const val BOTTOM_NAV_ITEM_SETTINGS= "Settings"

        const val ONBOARDING_SLIDE_TITLE_1= "DO TODAY"
        const val ONBOARDING_SLIDE_TITLE_2= "PLAN"
        const val ONBOARDING_SLIDE_TITLE_3= "TRACK"

        const val ONBOARDING_SLIDE_DESC_1= "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod"
        const val ONBOARDING_SLIDE_DESC_2= ONBOARDING_SLIDE_DESC_1
        const val ONBOARDING_SLIDE_DESC_3= ONBOARDING_SLIDE_DESC_1

        const val HOME_MENU_ITEM_DO_TODAY = "Do Today"
        const val HOME_MENU_ITEM_TIPS = "Activities & Tips"
        const val HOME_MENU_ITEM_TRACK_IT = "Track It"
        const val HOME_MENU_ITEM_PLAN = "Plan"
        const val HOME_MENU_ITEM_TRAINING = "Training"
        const val HOME_MENU_ITEM_SHARE = "Say & Share"

        const val EMPTY_BODY = "Empty Body"
        const val EMPTY_CREDENTAILS = "Please enter your credentials"
        const val PASSWORD_CREDENTIALS = "Password must be at least 6 character long"
        const val NO_PASS_MATCH = "Your password and confirm password does not matches"
        const val PASS_NOT_UPDATED = "Your password has not been updated"
        const val NO_INTERNET = "Internet Unavailable"



    }

}