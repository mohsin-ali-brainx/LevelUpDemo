package com.example.levelup.interfaces

import com.example.levelup.models.*
import com.example.levelup.utils.ApiConstants
import retrofit2.Response
import retrofit2.http.*

interface LevelUpApiInterface {
    @POST(ApiConstants.POST_SIGN_IN_API)
    suspend fun signIn(@Header("Content-Type") header:String , @Body signedInUser: User):Response<SignedInUser>


    @POST(ApiConstants.POST_FIRST_TIME_PASSWORD_API)
    suspend fun firstTimePasswordChange(@HeaderMap header:Map<String,String>,
                                        @Body signedInUser: User
                                        ):Response<UpdatePassword>


    @DELETE(ApiConstants.DEL_SIGN_OUT_API)
    suspend fun signOut(@HeaderMap header:Map<String,String>):Response<UserSignout>

    @GET(ApiConstants.GET_RANDOM_QUOTE_API)
    suspend fun randomQuote(@HeaderMap header:Map<String,String>):Response<RandomQuote>

}
