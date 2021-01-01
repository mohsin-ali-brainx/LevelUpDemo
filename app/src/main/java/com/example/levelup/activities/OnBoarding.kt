package com.example.levelup.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.levelup.LevelUpApplication
import com.example.levelup.activityViewModels.OnBoardingViewModel
import com.example.levelup.adapters.OnBoardingViewPagerAdapter
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivityOnBoardingBinding

class OnBoarding : BaseActivity() {


    private lateinit var viewmodel : OnBoardingViewModel
    private lateinit var binding:ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        settingViewPager()
        settingButton()
        viewmodel.stopOnboardingScreen()
    }

    private fun settingButton() {
        binding.btnGetStarted.setOnClickListener {

            moveToLogin()
        }
    }

    private fun setBinding() {
        viewmodel = ViewModelProvider(this).get(OnBoardingViewModel::class.java)
        binding= ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun settingViewPager() {
        binding.vpOnboarding.apply {
            adapter =OnBoardingViewPagerAdapter(LevelUpApplication.getTmContext(), viewmodel.getOnBoardingSlides())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        binding.vpOnboarding.adapter =OnBoardingViewPagerAdapter(LevelUpApplication.getTmContext(), viewmodel.getOnBoardingSlides())
        binding.circularIndicator.setViewPager(binding.vpOnboarding)
        viewPagerOnScrollListener()
    }

    private fun viewPagerOnScrollListener() {
        binding.vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position==2)
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun moveToLogin() {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        finish()
    }

}