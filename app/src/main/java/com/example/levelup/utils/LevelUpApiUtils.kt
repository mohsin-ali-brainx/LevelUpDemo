package com.example.levelup.utils

import com.example.levelup.api.LevelUpRetrofitClient
import com.example.levelup.interfaces.LevelUpApiInterface

object LevelUpApiUtils {
    val levelUpApi = LevelUpRetrofitClient.getRetrofit().create(LevelUpApiInterface::class.java)
}