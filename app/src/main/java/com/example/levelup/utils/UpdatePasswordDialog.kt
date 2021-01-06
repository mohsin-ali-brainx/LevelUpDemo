package com.example.levelup.utils

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.levelup.R
import java.util.concurrent.TimeUnit

class UpdatePasswordDialog :
        LevelUpDialogUtils()
{

    private lateinit var listner: UpdatePasswordDialogListner
    private lateinit var countdownTimer: CountDownTimer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.dialog_update_password, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listner  =  context as UpdatePasswordDialogListner

        } catch (e: ClassCastException) {
            throw ClassCastException("Calling Fragment must implement Update Password Listner")
        }
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    private fun startTimer() {
        val duration:Long = TimeUnit.SECONDS.toMillis(3)
        countdownTimer = object : CountDownTimer(duration, 1000) {
            override fun onFinish() {
                listner.isUpdated(true)
               dismiss()
            }
            override fun onTick(p0: Long) {

            }
        }
        countdownTimer.start()
    }

    interface UpdatePasswordDialogListner{
        fun isUpdated(updated: Boolean)
    }

}