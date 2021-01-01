package com.example.levelup.baseClasses

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

open class BaseFragment: Fragment() {

    fun getLoadingObserver():Observer<Boolean>{
        val requiredActivity = requireActivity() as BaseActivity
        return requiredActivity.loadingObserver
    }

    fun String.showToast() {
        (activity as? BaseActivity)?.showToast(this)
    }

    fun hideLoading(){
        (activity as? BaseActivity)?.hideProcessingDialog()
    }

    fun showLoading(){
        (activity as? BaseActivity)?.showProcessingDialog()
    }


}