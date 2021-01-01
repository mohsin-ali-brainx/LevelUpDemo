package com.example.levelup.utils

import android.app.Activity
import android.content.Context
import android.os.CountDownTimer
import androidx.core.content.ContextCompat
import com.example.levelup.R
import com.kaopiz.kprogresshud.KProgressHUD

object LevelUpProgress {
    private const val COUNT_DOWN_INTERVAL = 500L
    private var mProgressDialogLib: KProgressHUD? = null
    private var dataChangeTimer: CountDownTimer? = null

    fun show(context: Context, title: String? = null, msTime: Long? = null) {
        if ((context as Activity).isFinishing) return
        if ((mProgressDialogLib != null && mProgressDialogLib!!.isShowing)) dismiss()
        dataChangeTimer?.cancel()
        dataChangeTimer = null

        KProgressHUD.create(context).apply {
            setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            setBackgroundColor(ContextCompat.getColor(context, R.color.progress_bg_color))
            setCancellable(false)
            setAnimationSpeed(2)
            setDimAmount(0.5f)
            if (!title.isNullOrEmpty())
                setLabel(title)
            show()
            mProgressDialogLib = this
        }
        if (msTime != null) startTimer(msTime)
    }

    fun isShowing(): Boolean = mProgressDialogLib?.isShowing ?: false


    fun dismiss() {
        dataChangeTimer?.cancel()
        dataChangeTimer = null
        mProgressDialogLib?.dismiss()
    }

    private fun startTimer(msTime: Long) {
        dataChangeTimer = object : CountDownTimer(msTime, COUNT_DOWN_INTERVAL) {
            override fun onFinish() {
                dismiss()
            }

            override fun onTick(millisUntilFinished: Long) = Unit
        }
        dataChangeTimer?.start()
    }
}