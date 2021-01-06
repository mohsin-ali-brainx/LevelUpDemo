package com.example.levelup.utils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.levelup.R

class LogoutDialog : LevelUpDialogUtils() {
    private val TAG = "MyCustomDialog"
    private var listner: LogoutDialogListner? = null
    private lateinit var logoutDialogView:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logoutDialogView =  inflater.inflate(R.layout.dialog_logout, container, false)
        clickEvents()
        return logoutDialogView
    }
    private fun clickEvents() {
        logoutDialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            listner?.isLogoutClicked(false)
            dismiss()
        }
        logoutDialogView.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            listner?.isLogoutClicked(true)
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
        listner=null
    }

    interface LogoutDialogListner{
        fun isLogoutClicked(isClicked: Boolean)
    }
}