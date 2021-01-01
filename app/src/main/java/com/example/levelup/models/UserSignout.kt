package com.example.levelup.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserSignout(
        @SerializedName("success")
        @Expose
        var success: Boolean? = null,
) : Serializable