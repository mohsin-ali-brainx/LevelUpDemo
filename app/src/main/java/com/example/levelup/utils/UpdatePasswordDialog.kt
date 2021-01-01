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

    private lateinit var listner: UpdatePasswordDialog.UpdatePasswordDialogListner
    lateinit var countdown_timer: CountDownTimer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_update_password, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listner  =  context as UpdatePasswordDialogListner;

        } catch (e: ClassCastException) {
            throw ClassCastException("Calling Fragment must implement Update Password Listner")
        }
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun startTimer() {
        val duration:Long = TimeUnit.SECONDS.toMillis(3)
        countdown_timer = object : CountDownTimer(duration, 1000) {
            override fun onFinish() {
                listner.isUpdated(true)
               dismiss()
            }
            override fun onTick(p0: Long) {

            }
        }
        countdown_timer.start()
    }

    interface UpdatePasswordDialogListner{
        fun isUpdated(updated: Boolean)
    }

}