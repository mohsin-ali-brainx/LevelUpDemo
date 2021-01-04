package com.example.levelup.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

open class LevelUpDialogUtils : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.apply { getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
    }

}