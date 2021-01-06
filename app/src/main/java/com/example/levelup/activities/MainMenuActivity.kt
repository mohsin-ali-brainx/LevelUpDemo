package com.example.levelup.activities

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.R
import com.example.levelup.activityViewModels.MainMenuViewModel
import com.example.levelup.adapters.MainMenuPagerAdapter
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivityMainMenuBinding
import com.example.levelup.utils.LevelUpUtils
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.bottom_nav_items.view.*

class MainMenuActivity : BaseActivity() {

    private lateinit var viewmodel: MainMenuViewModel
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var mainPagerAdapter: MainMenuPagerAdapter
    private val owner = this@MainMenuActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBinding()
        setViewPagerAdapter()

    }


    private fun setViewBinding() {
        viewmodel = ViewModelProvider(this).get(MainMenuViewModel::class.java)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setViewPagerAdapter() {
        binding.viewPager.offscreenPageLimit = 3
        mainPagerAdapter = MainMenuPagerAdapter(supportFragmentManager)
        mainPagerAdapter.apply {
            addFragments(viewmodel.getFragmentList())
            binding.viewPager.adapter = this
            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }
        setTabs()
        selectTab(0)
        binding.tabLayout.addOnTabSelectedListener(pagerTabListener)
    }

    private fun selectTab(index: Int) {
        binding.viewPager.currentItem = index
        changeTabIconAndTextColor(binding.tabLayout.getTabAt(index)?.customView, true)
    }

    private fun changeTabIconAndTextColor(customView: View?, isSelected: Boolean) {
        val textColor = if (isSelected) R.color.black_text_color else R.color.grey_text_color
        val iconColor = if (isSelected) R.color.theme_color else R.color.grey_text_color
        customView?.apply {
            title?.setTextColor(resources.getColor(textColor))
            item_icon?.colorFilter =
                LevelUpUtils.getFilterColor(
                    owner,
                    iconColor
                )

        }
    }

    private fun setTabs() {
        (0 until binding.tabLayout.tabCount).forEach { i ->
            binding.tabLayout.getTabAt(i)?.apply {
                setCustomView(R.layout.bottom_nav_items)
                customView?.apply {
                    if (i == 1) ic_badge.visibility = View.VISIBLE
                    else ic_badge.visibility = View.GONE
                    item_icon?.setImageResource(viewmodel.getTabIcons()[i])
                    title?.apply {
                        text = viewmodel.getTitleList()[i]
                        setTypeface(this.typeface, Typeface.BOLD)
                    }
                }
            }
        }
    }

    private val pagerTabListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            changeTabIconAndTextColor(tab.customView, true)
            hideKeyboard()
        }
        override fun onTabUnselected(tab: TabLayout.Tab) =
            changeTabIconAndTextColor(tab.customView, false)

        override fun onTabReselected(tab: TabLayout.Tab) = Unit
    }


}