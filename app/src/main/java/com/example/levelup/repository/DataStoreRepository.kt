package com.example.levelup.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.createDataStore
import com.example.levelup.models.SignedInUser
import com.example.levelup.utils.LevelUpConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class DataStoreRepository(context:Context) {
    private  val datastore: DataStore<Preferences> = context.createDataStore(
        name = LevelUpConstants.DATASTORE_PREFERENCE_NAME
    )

    suspend fun stopShowingOnboardingScreen(){
        datastore.edit {
            it[LevelUpConstants.SHOW_ONBOARDING_KEY]=true
        }
    }

    suspend fun setLogoutUser(){
        datastore.edit {
            it[LevelUpConstants.USER_LOGEDIN_KEY]=false
        }
    }

    suspend fun setUserLoggedIn(user:SignedInUser){
        datastore.edit {
            it[LevelUpConstants.USER_LOGEDIN_KEY]=true
            it[LevelUpConstants.ACCESS_TOKEN_KEY]=user.access_token!!
            it[LevelUpConstants.UID_KEY]=user.uid!!
            it[LevelUpConstants.CLIENT_KEY]=user.client!!
            it[LevelUpConstants.USERNAME_KEY]=user.name!!
        }
    }


    val checkingOnBoardingScreen: Flow<Boolean> = datastore.data.catch { exception->
        if(exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { preference->
        val showOnBoarding = preference[LevelUpConstants.SHOW_ONBOARDING_KEY]?:false
        showOnBoarding
    }

    val checkOnboarding:Flow<Boolean> = datastore.data.map {
        it[LevelUpConstants.SHOW_ONBOARDING_KEY]?:true
    }


    val isUserLoggedIn: Flow<Boolean> = datastore.data.catch { exception->
        if(exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { preference->
        val isLoggedIn = preference[LevelUpConstants.USER_LOGEDIN_KEY]?:false
        isLoggedIn
    }

    val getUserDetail: Flow<Map<String,String>> = datastore.data.catch { exception->
        if(exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { preference->
        val userMap: HashMap<String, String> = hashMapOf()
        userMap[LevelUpConstants.USERNAME] = preference[LevelUpConstants.USERNAME_KEY]?:""
        userMap[LevelUpConstants.ACCESS_TOKEN] = preference[LevelUpConstants.ACCESS_TOKEN_KEY]?:""
        userMap[LevelUpConstants.UID] = preference[LevelUpConstants.UID_KEY]?:""
        userMap[LevelUpConstants.CLIENT] = preference[LevelUpConstants.CLIENT_KEY]?:""
        userMap.filter { it.value.isNotEmpty() } as HashMap<String, String>
    }

}