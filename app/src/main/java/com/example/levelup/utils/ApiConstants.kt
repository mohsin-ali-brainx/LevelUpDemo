package com.example.levelup.utils

class ApiConstants {
    companion object{
        const val BASE_URL = "https://brainx-levelup-staging.herokuapp.com/"
        private const val API = "/api"
        private const val VERSION = "/v1"
        private const val USER = "/user"
        private const val USERS = "/users"
        private const val QUOTES = "/quotes"
        private const val JSON = ".json"
        private const val SIGN_IN = "/sign_in$JSON"
        private const val SIGN_OUT = "/sign_out$JSON"
        private const val FIRST_TIME_PASSWORD = "/change_password$JSON"
        private const val RANDOM_QUOTE = "/random$JSON"

        const val POST_SIGN_IN_API = API+ VERSION + USERS + SIGN_IN
        const val DEL_SIGN_OUT_API = API+ VERSION + USERS + SIGN_OUT
        const val POST_FIRST_TIME_PASSWORD_API = API+ VERSION + USER + FIRST_TIME_PASSWORD
        const val GET_RANDOM_QUOTE_API = API+ VERSION + QUOTES+ RANDOM_QUOTE

    }
}