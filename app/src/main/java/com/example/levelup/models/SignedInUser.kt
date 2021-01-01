package com.example.levelup.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignedInUser(

        @SerializedName("id")
        @Expose var id: Int? = null,
        @SerializedName("uid")
        @Expose var uid: String? = null,
        @SerializedName("name")
        @Expose var name: String? = null,
        @SerializedName("device_token")
        @Expose var device_token: String? = null,
        @SerializedName("app_platform")
        @Expose var app_platform: String? = null,
        @SerializedName("app_version")
        @Expose var app_version: Int? = null,
        @SerializedName("active?")
        @Expose var active: Boolean? = null,
        @SerializedName("first_login?")
        @Expose var first_login: Boolean? = null,
        var access_token: String? = null,
        var client: String? = null,

) : Serializable