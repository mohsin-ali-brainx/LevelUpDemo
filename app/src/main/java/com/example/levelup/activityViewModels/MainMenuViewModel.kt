package com.example.levelup.activityViewModels

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.levelup.R
import com.example.levelup.baseClasses.BaseViewModel
import com.example.levelup.fragments.HomeFragment
import com.example.levelup.fragments.NotificationsFragment
import com.example.levelup.fragments.SettingsFragment
import com.example.levelup.utils.LevelUpConstants

class MainMenuViewModel(application: Application): BaseViewModel(application) {
    private var fragmentList: MutableList<Fragment> = mutableListOf()
    private val tabTitles: MutableList<String> = mutableListOf()
    private val tabIcons: MutableList<Int> = mutableListOf()

    var textColor:Int = R.color.black_text_color
    private var iconColor:Int = R.color.theme_color

    init {
        fragmentList.add(HomeFragment())
        fragmentList.add(NotificationsFragment())
        fragmentList.add(SettingsFragment())

        tabIcons.add(R.drawable.ic_home_inactive)
        tabIcons.add(R.drawable.ic_notification_inactive)
        tabIcons.add(R.drawable.ic_settings_inactive)

        tabTitles.add(LevelUpConstants.BOTTOM_NAV_ITEM_HOME)
        tabTitles.add(LevelUpConstants.BOTTOM_NAV_ITEM_NOTIFICATION)
        tabTitles.add(LevelUpConstants.BOTTOM_NAV_ITEM_SETTINGS)
    }

    fun changeSelectedTabColor(){
        textColor = R.color.black_text_color
        iconColor = R.color.theme_color
    }

    fun changeUnSelectedTabColor(){
        textColor = R.color.grey_text_color
        iconColor = R.color.grey_text_color
    }

    fun getFragmentList():MutableList<Fragment>{
        return this.fragmentList
    }

    fun getTabIcons():MutableList<Int>{
        return this.tabIcons
    }

    fun getTitleList():MutableList<String>{
        return this.tabTitles
    }

}