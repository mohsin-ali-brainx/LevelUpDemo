package com.example.levelup.utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

open class LevelUpDialogUtils : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDialog()?.apply {
           // getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
           // getWindow()?.setBackgroundDrawable(ColorDrawable(R.drawable.bg_white_cornered))
        }


    }

}