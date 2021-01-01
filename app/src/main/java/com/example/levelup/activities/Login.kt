package com.example.levelup.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.activityViewModels.LoginViewModel
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivityLoginBinding

class Login : BaseActivity() {

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
        binding.listnerHandler = this
        binding.viewModel = viewmodel
        setContentView(binding.root)
        viewmodel.isLoading.observe(this,loadingObserver)
        viewmodel.errorObserver.observe(this,errorObserver)

    }

    fun toggleEvent(view: View) {
        if (viewmodel.showPassword) {
            viewmodel.showPassword = false
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            viewmodel.showPassword = true
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
        binding.etPassword.setSelection(binding.etPassword.text!!.length)
    }

    fun LoginClicked(view: View) {
        viewmodel.loginUser()
    }


    private fun setObservables() {
        viewmodel.logedInUser.observe(this,{user->
            if (user?.first_login==true){
                moveToUpdatePassword()
            }else{
                moveToMainScreen()
            }
        })
    }

    private fun moveToUpdatePassword() {
        val intent = Intent(this,UpdatePassword::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToMainScreen() {
        val intent = Intent(this,MainMenu::class.java)
        startActivity(intent)
        finish()
    }



}