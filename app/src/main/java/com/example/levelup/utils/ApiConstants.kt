package com.example.levelup.utils

class ApiConstants {
    companion object{
        const val BASE_URL = "https://brainx-levelup-staging.herokuapp.com/"
        const val API = "/api"
        const val VERSION = "/v1"
        const val USER = "/user"
        const val USERS = "/users"
        const val QUOTES = "/quotes"
        const val JSON = ".json"
        const val SIGN_IN = "/sign_in"+ JSON
        const val SIGN_OUT = "/sign_out"+ JSON
        const val FIRST_TIME_PASSWORD = "/change_password"+ JSON
        const val RANDOM_QUOTE = "/random"+ JSON

        const val POST_SIGN_IN_API = API+ VERSION + USERS + SIGN_IN
        const val DEL_SIGN_OUT_API = API+ VERSION + USERS + SIGN_OUT
        const val POST_FIRST_TIME_PASSWORD_API = API+ VERSION + USER + FIRST_TIME_PASSWORD
        const val GET_RANDOM_QUOTE_API = API+ VERSION + QUOTES+ RANDOM_QUOTE

    }
}