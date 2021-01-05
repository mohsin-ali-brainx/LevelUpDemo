package com.example.levelup.activities

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.LevelUpApplication
import com.example.levelup.activityViewModels.LoginViewModel
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivityLoginBinding
import com.example.levelup.extensions.startLevelUpActivity
import com.example.levelup.utils.LevelUpConstants

class LoginActivity : BaseActivity() {

    private lateinit var viewmodel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setObservables()
    }

    private fun setBinding() {
        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.apply {
            viewModel = viewmodel
        }
        setContentView(binding.root)
        setViewModelObserver()
    }

    private fun setViewModelObserver() {
        viewmodel.apply {
            isLoading.observe( this@LoginActivity,loadingObserver)
            errorMessageObserver.observe( this@LoginActivity,errorObserver)
        }
    }

    private fun setObservables() {
        viewmodel.logedInUser.observe(this,{user->
            when(user.first_login){
                true->  startLevelUpActivity(activityClass=UpdatePasswordActivity::class.java,isFinish = true)
                false-> startLevelUpActivity(activityClass=MainMenuActivity::class.java,isFinish = true)
            }
        })
    }

    fun toggleClicked(v: View) {

        when(viewmodel.showPassword){
            true->{
                viewmodel.showPassword = false
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            false->{
                viewmodel.showPassword = true
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
        }
        binding.etPassword.setSelection(binding.etPassword.text!!.length)

    }

    fun loginClicked(view: View) {
        if(LevelUpApplication.isInternetConnected)
            viewmodel.loginUser()
        else
            showErrorDialog(LevelUpConstants.NO_INTERNET)
    }



}