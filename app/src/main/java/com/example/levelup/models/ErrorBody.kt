package com.example.levelup.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorBody {
    @SerializedName("error")
    @Expose
    val error:String?=null
}