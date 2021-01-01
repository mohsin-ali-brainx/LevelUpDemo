package com.example.levelup.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.activityViewModels.UpdatePasswordViewModel
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivityUpdatePasswordBinding
import com.example.levelup.utils.LevelUpConstants
import com.example.levelup.utils.UpdatePasswordDialog

class UpdatePassword : BaseActivity(),UpdatePasswordDialog.UpdatePasswordDialogListner {

    private lateinit var viewmodel: UpdatePasswordViewModel
    private lateinit var binding: ActivityUpdatePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        updatePasswordObserver()

    }

    private fun setBinding() {
        viewmodel = ViewModelProvider(this).get(UpdatePasswordViewModel::class.java)
        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        binding.listnerHandler = this
        binding.viewModel = viewmodel
        setContentView(binding.root)
        viewmodel.isLoading.observe(this,loadingObserver)
    }

    fun UpdateClicked(view: View) {
        this.authHeaders {
         viewmodel.updateUserPassword(it)
        }

       // UpdatePasswordDialog().show(supportFragmentManager, LevelUpConstants.UPDATEPASSWORD_DIALOG_TAG)

    }

    fun updatePasswordObserver(){
        viewmodel.passwordUpdated.observe(this,{
            if(it == true)
                UpdatePasswordDialog().show(supportFragmentManager, LevelUpConstants.UPDATEPASSWORD_DIALOG_TAG)
        })
    }

    private fun moveToMainScreen() {
        val intent = Intent(this,MainMenu::class.java)
        startActivity(intent)
        finish()
    }

    override fun isUpdated(updated: Boolean) {
        if (updated==true){
            moveToMainScreen()
        }
    }


}