package com.example.levelup.repository

import com.example.levelup.interfaces.IResponse
import com.example.levelup.models.*
import com.example.levelup.utils.LevelUpApiUtils
import com.example.levelup.utils.LevelUpConstants
import com.example.levelup.utils.LevelUpUtils

class RetrofitRepository{
    private val apiService = LevelUpApiUtils.levelUpApi


    suspend fun randomQuote(header:Map<String,String>,listener: IResponse<RandomQuote, String>){
        val response =apiService.randomQuote(header)
        if (response.isSuccessful){
            if (response.body()!=null){
                listener.onSuccess(response.body()!!)
            }
            else {
                listener.onFailure(LevelUpConstants.EMPTY_BODY)
            }

        }else{
            listener.onFailure(LevelUpUtils.jsonConversion(response.errorBody()?.string()))
        }
    }

    suspend fun signInUser( user: User, listener: IResponse<SignedInUser, String>){
        val response =apiService.signIn(LevelUpConstants.CONTENT_TYPE,user)
        if (response.isSuccessful){
            if (response.body()!=null){
                val logedInUser = response.body()
                logedInUser?.apply {
                    client = response.headers().get("client")
                    access_token = response.headers().get("access-token")
                }
                listener.onSuccess(logedInUser!!)
            }
            else {
                listener.onFailure(LevelUpConstants.EMPTY_BODY)
            }

        }else{
            listener.onFailure(LevelUpUtils.jsonConversion(response.errorBody()?.string()))
        }
    }

    suspend fun updatePassword(header:Map<String,String>, user:User, listener: IResponse<UpdatePassword, String>){
        val response =apiService.firstTimePasswordChange(header, user)
        if (response.isSuccessful){
            if (response.body()!=null){
                val updatePassword = response.body()
                listener.onSuccess(updatePassword!!)
            }
            else {
                listener.onFailure(LevelUpConstants.EMPTY_BODY)
            }

        }else{
            listener.onFailure(LevelUpUtils.jsonConversion(response.errorBody()?.string()))
        }
    }

    suspend fun signOut(header:Map<String,String>, listener: IResponse<UserSignout, String>){
        val response =apiService.signOut(
                header,
        )
        if (response.isSuccessful){
            if (response.body()!=null){
                val updatePassword = response.body()
                listener.onSuccess(updatePassword!!)
            }
            else {
                listener.onFailure(LevelUpConstants.EMPTY_BODY)
            }

        }else{
            listener.onFailure(LevelUpUtils.jsonConversion(response.errorBody()?.string()))
        }
    }

}