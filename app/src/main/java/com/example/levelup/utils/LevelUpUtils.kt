package com.example.levelup.utils

import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.content.ContextCompat
import com.example.levelup.models.ErrorBody
import com.google.gson.Gson

object LevelUpUtils {


    fun getFilterColor(context: Context, colorId: Int): ColorFilter {
        return PorterDuffColorFilter(
            ContextCompat.getColor(
                context,
                colorId
            ), PorterDuff.Mode.SRC_ATOP
        )
    }


    fun jsonConversion(errorBody: String?, defaultError: String = "Server Error."): String {
        try {
            return Gson().fromJson(
                errorBody,
                ErrorBody::class.java
            ).error ?: defaultError
        } catch (e: Exception) {
            return defaultError
        }
    }

}