package com.example.levelup.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.levelup.activities.Login
import com.example.levelup.activities.MainMenu
import com.example.levelup.baseClasses.BaseFragment
import com.example.levelup.databinding.FragmentSettingsBinding
import com.example.levelup.fragmentViewModels.SettingsViewModel
import com.example.levelup.utils.LevelUpConstants
import com.example.levelup.utils.LogoutDialog

class Settings : BaseFragment(),LogoutDialog.LogoutDialogListner {

    private lateinit var viewmodel: SettingsViewModel
    private  var binding: FragmentSettingsBinding?=null
    private lateinit var requiredActivity: MainMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        requiredActivity = requireActivity() as MainMenu
        viewmodel = ViewModelProviders.of(this)
                .get(SettingsViewModel::class.java)
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding?.listnerHandler = this
        viewmodel.isLoading.observe(requiredActivity,getLoadingObserver())
        logoutObserver()
        return binding!!.root
    }

    fun logoutClicked(view: View){

        val fm: FragmentManager = requiredActivity.getSupportFragmentManager()
        val logoutDialog = LogoutDialog()
        logoutDialog.setTargetFragment(this,0)
        logoutDialog.show(fm,LevelUpConstants.LOGOUT_DIALOG_TAG)


    }

    override fun isLogoutClicked(isClicked: Boolean) {
        when(isClicked){
            true -> {
                requiredActivity.authHeaders {
                    viewmodel.signOutUser(it)
                }
            }
            false -> {

            }
        }
    }

    private fun moveToLogin() {
       activity?.let {
           val intent = Intent(it,Login::class.java)
           it.startActivity(intent)
           it.finish()
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


    fun logoutObserver(){
        viewmodel.logout.observe(requiredActivity,{
            if (it==true){
                viewmodel.setUserLogout()
                hideLoading()
                moveToLogin()
            }
        })
    }

}