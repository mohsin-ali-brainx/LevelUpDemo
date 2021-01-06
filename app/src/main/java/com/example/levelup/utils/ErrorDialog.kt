package com.example.levelup.utils

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.levelup.R
import kotlinx.android.synthetic.main.dialog_error.view.*
import java.util.concurrent.TimeUnit

class ErrorDialog(private val error_msg:String):LevelUpDialogUtils() {

    private lateinit var dialogView: View
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogView =  inflater.inflate(R.layout.dialog_error, container, false)
        dialogView.tv_error_msg.text = error_msg

        return dialogView
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    private fun startTimer() {
        val duration:Long = TimeUnit.SECONDS.toMillis(3)
        countDownTimer = object : CountDownTimer(duration, 1000) {
            override fun onFinish() {
                dismiss()
            }
            override fun onTick(p0: Long) {

            }
        }
        countDownTimer.start()
    }


}