package com.example.levelup.interfaces

interface IResponse<in T, in E> {
    fun onSuccess(result: T)
    fun onFailure(error: E)
}