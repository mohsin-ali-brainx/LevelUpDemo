package com.example.levelup.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.activityViewModels.SplashViewModel
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivitySplashBinding
import com.example.levelup.extensions.startLevelUpActivity

class SplashActivity : BaseActivity() {

    private lateinit var viewBinding: ActivitySplashBinding
    private lateinit var viewmodel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setSplashAnimation()
    }

    private fun setBinding() {
        viewmodel = ViewModelProvider(this).get(SplashViewModel::class.java)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }


    private fun setSplashAnimation() {
        viewBinding.logoImageView.apply {
            this.alpha = 0f
            this.animate().setDuration(3000).alpha(1f).withEndAction {
                moveToNewScreen()
            }
        }
    }

    private fun moveToNewScreen() {

        viewmodel.shouldShowOnBoardingScreen.observe(this, { showOnBoardingScreen ->
            if (showOnBoardingScreen == true) {
                checkUserSession()
            } else {
                startLevelUpActivity(activityClass=OnBoardingActivity::class.java,isFinish = true)
            }
        })


    }

    private fun checkUserSession() {
        viewmodel.isUserLoggedIn.observe(this, { isLoggedIn ->
            print(isLoggedIn)
            if (isLoggedIn == true)
               startLevelUpActivity(activityClass=MainMenuActivity::class.java,isFinish = true)
            else
                startLevelUpActivity(activityClass=LoginActivity::class.java,isFinish = true)
        })

    }



}