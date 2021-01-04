package com.example.levelup.utils

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.levelup.databinding.DialogLogoutBinding

class LogoutDialog : LevelUpDialogUtils() {

    private val TAG = "MyCustomDialog"

    private lateinit var listner:LogoutDialogListner
    private var binding:DialogLogoutBinding?=null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       binding = DialogLogoutBinding.inflate(LayoutInflater.from(context))
        clickEvents()
        return AlertDialog.Builder(requireActivity())
                .setView(binding!!.root)
                .create()

    }

    private fun clickEvents() {

        binding?.btnCancel?.setOnClickListener{
            listner.isLogoutClicked(false)
            dismiss()
        }
        binding?.btnLogout?.setOnClickListener{
            listner.isLogoutClicked(true)
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            listner = targetFragment as LogoutDialogListner

        } catch (e: ClassCastException) {
            throw ClassCastException("Calling Fragment must implement LogoutListener")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    interface LogoutDialogListner{
        fun isLogoutClicked(isClicked: Boolean)
    }


}