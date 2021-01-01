package com.example.levelup.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UpdatePassword(
        @SerializedName("message")
        @Expose var message: String? = null,
) : Serializable