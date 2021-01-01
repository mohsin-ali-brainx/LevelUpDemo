package com.example.levelup.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainMenuPagerAdapter(fm:FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    private lateinit var fragmentList: List<Fragment>
    //private lateinit var titleList: List<String>

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

//    override fun getPageTitle(position: Int): CharSequence? =
//        if (titleList.size > position) titleList[position] else ""

    fun addFragments(fragments: List<Fragment>) {
        fragmentList = fragments
        //titleList = tiles
    }

}