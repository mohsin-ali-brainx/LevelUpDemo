package com.example.levelup.api

import com.example.levelup.utils.ApiConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

object LevelUpRetrofitClient {
    private var baseLevelUpRetrofit: Retrofit? = null
    fun getRetrofit(): Retrofit {
        if (baseLevelUpRetrofit == null) {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                if (response.code() == 401) {
//                    EventBus.getDefault().post(SessionEvent(true))
//                    CustomCallback.canceled = true
                }
                response
            }
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
            httpClient.addInterceptor(logging)
            val client = httpClient.build()
            val builder = GsonBuilder()
            builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            builder.create()
            baseLevelUpRetrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .client(client)
                    .addConverterFactory(
                            GsonConverterFactory.create(
                                    GsonBuilder()
                                            //.excludeFieldsWithoutExposeAnnotation()
                                            .create()
                            )
                    )
                    .build()
        }
        return baseLevelUpRetrofit!!
    }

}