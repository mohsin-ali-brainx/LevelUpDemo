package com.example.levelup.utils

import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.content.ContextCompat

object LevelUpUtils {


    fun getFilterColor(context: Context, colorId: Int): ColorFilter {
        return PorterDuffColorFilter(
            ContextCompat.getColor(
                context,
                colorId
            ), PorterDuff.Mode.SRC_ATOP
        )
    }
}