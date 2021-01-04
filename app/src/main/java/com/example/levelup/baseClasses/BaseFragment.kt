package com.example.levelup.baseClasses

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

open class BaseFragment: Fragment() {

    fun getLoadingObserver():Observer<Boolean>{
        val requiredActivity = requireActivity() as BaseActivity
        return requiredActivity.loadingObserver
    }

    fun getErrorObserver():Observer<Any?>{
        val requiredActivity = requireActivity() as BaseActivity
        return requiredActivity.errorObserver
    }

    fun String.showToast() {
        (activity as? BaseActivity)?.showToast(this)
    }

    fun String.errorDialog() {
        (activity as? BaseActivity)?.showErrorDialog(this)
    }


    fun hideLoading(){
        (activity as? BaseActivity)?.hideProcessingDialog()
    }

    fun showLoading(){
        (activity as? BaseActivity)?.showProcessingDialog()
    }


}