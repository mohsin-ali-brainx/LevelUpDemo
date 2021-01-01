package com.example.levelup.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

        @SerializedName("email")
        @Expose var email: String? = null,
        @SerializedName("password")
        @Expose var password: String? = null,
        @SerializedName("password_confirmation")
        @Expose var password_confirmation: String? = null,
        @SerializedName("device_token")
        @Expose var device_token: String = "AAAAR5AMOm4:APA91bEJqOls8Hntj0Ki-umpklMNMue8WZbIA8Ry-F31rVk7aNB9LAVnC3EFockxGCxCELPk5BiuwKO-bivmMl5eIZE2p4qRT3LaMIzzf9cgOx8ehCwwtwePh5N0KfxkQUJ-SOGBNw8L",
        @SerializedName("app_platform")
        @Expose var app_platform: String = "android",
        @SerializedName("app_version")
        @Expose var app_version: Int = 1,

)