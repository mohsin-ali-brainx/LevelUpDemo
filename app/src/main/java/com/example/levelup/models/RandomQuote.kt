package com.example.levelup.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RandomQuote(
        @SerializedName("author")
        @Expose var author: String? = null,
        @SerializedName("text")
        @Expose var text: String? = null
) : Serializable{
        fun autherParcelable():String{
                return    "By ${author}"
        }

        fun quoteParcelable():String{
                return    "\"${text}\""
        }
}