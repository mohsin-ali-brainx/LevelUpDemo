package com.example.levelup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.levelup.LevelUpApplication
import com.example.levelup.activities.LoginActivity
import com.example.levelup.activities.MainMenuActivity
import com.example.levelup.baseClasses.BaseFragment
import com.example.levelup.databinding.FragmentSettingsBinding
import com.example.levelup.extensions.startLevelUpActivity
import com.example.levelup.fragmentViewModels.SettingsViewModel
import com.example.levelup.utils.LevelUpConstants
import com.example.levelup.utils.LogoutDialog
import com.example.levelup.utils.Status


class SettingsFragment : BaseFragment(),LogoutDialog.LogoutDialogListner {

    private lateinit var viewmodel: SettingsViewModel
    private  var binding: FragmentSettingsBinding?=null
    private lateinit var requiredActivity: MainMenuActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        requiredActivity = requireActivity() as MainMenuActivity
        viewmodel = ViewModelProviders.of(this)
            .get(SettingsViewModel::class.java)
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding?.listnerHandler = this
        setViewModelObserver()
        logoutObserver()
        return binding!!.root
    }

    private fun setViewModelObserver() {
        viewmodel.apply {
            isLoading.observe(requiredActivity,getLoadingObserver())
            errorMessageObserver.observe(requiredActivity,getErrorObserver())
        }
    }

    fun logoutClicked(view: View){

        val fm: FragmentManager = requiredActivity.supportFragmentManager
        val logoutDialog = LogoutDialog()
        logoutDialog.setTargetFragment(this,0)
        logoutDialog.show(fm,LevelUpConstants.LOGOUT_DIALOG_TAG)
    }

    override fun isLogoutClicked(isClicked: Boolean) {
        if (isClicked) {
            if (LevelUpApplication.isInternetConnected){
                requiredActivity.authHeaders {
                    viewmodel.signOutUser(it)
                }
            }else{
                LevelUpConstants.NO_INTERNET.showToast()
            }

        }
    }

    private fun moveToLogin() {
        activity?.startLevelUpActivity(activityClass= LoginActivity::class.java,isFinish = true)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


    private fun logoutObserver(){
        viewmodel.logout.observe(requiredActivity,{
            if (it==true){
                moveToLogin()
            }
        })
    }





}