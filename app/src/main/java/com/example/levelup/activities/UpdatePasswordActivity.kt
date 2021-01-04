package com.example.levelup.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.levelup.LevelUpApplication
import com.example.levelup.activityViewModels.UpdatePasswordViewModel
import com.example.levelup.baseClasses.BaseActivity
import com.example.levelup.databinding.ActivityUpdatePasswordBinding
import com.example.levelup.extensions.startLevelUpActivity
import com.example.levelup.utils.LevelUpConstants
import com.example.levelup.utils.UpdatePasswordDialog

class UpdatePasswordActivity : BaseActivity(),UpdatePasswordDialog.UpdatePasswordDialogListner {

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
        binding.apply {
            listnerHandler = this@UpdatePasswordActivity
            viewModel = viewmodel
        }
        setContentView(binding.root)
        viewmodel.apply {
            isLoading.observe(this@UpdatePasswordActivity,loadingObserver)
            errorMessageObserver.observe(this@UpdatePasswordActivity,errorObserver)
        }

    }

    fun UpdateClicked(view: View) {
        if(LevelUpApplication.isInternetConnected) {
            authHeaders {
                viewmodel.updateUserPassword(it)
            }
        }
        else{
            showErrorDialog(LevelUpConstants.NO_INTERNET)
        }
    }

    private fun updatePasswordObserver(){
        viewmodel.passwordUpdated.observe(this,{
            if(it == true)
                UpdatePasswordDialog().show(supportFragmentManager, LevelUpConstants.UPDATEPASSWORD_DIALOG_TAG)
        })
    }

    override fun isUpdated(updated: Boolean) {
        if (updated){
            startLevelUpActivity(activityClass=MainMenuActivity::class.java,isFinish = true)
        }
    }


}