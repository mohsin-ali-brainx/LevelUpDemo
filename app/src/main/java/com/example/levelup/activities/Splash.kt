package com.example.levelup.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.activityViewModels.SplashViewModel
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivitySplashBinding

class Splash : BaseActivity() {

    private lateinit var viewBinding: ActivitySplashBinding
    private lateinit var viewmodel: SplashViewModel
    //private var showOnBaording: Boolean = true
   // private var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setSplashAnimation()
    }

    fun setBinding() {
        viewmodel = ViewModelProvider(this).get(SplashViewModel::class.java)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }


    private fun setSplashAnimation() {
        viewBinding.logoImageView.apply {
            this.alpha = 0f
            this.animate().setDuration(3000).alpha(1f).withEndAction {
                moveToNewScreen()
               // moveToOnBoarding()
            }
        }
    }

    private fun moveToNewScreen() {

        viewmodel.readShowOnBoarding.observe(this, { showOnBaordingScreen ->
            if (showOnBaordingScreen == true) {
                checkForLogedInUser()
            } else {
                moveToOnBoarding()
            }
        })


    }

    private fun checkForLogedInUser() {
        viewmodel.isUserLoggedIn.observe(this, { isLoggedIn ->
            print(isLoggedIn)
            if (isLoggedIn == true)
                moveToHome()
            else
                moveToLogin()
        })

    }

    private fun moveToHome() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun moveToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun moveToOnBoarding() {
        val intent = Intent(this, OnBoarding::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }


}