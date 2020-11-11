package com.xyz.retrofit_kotlin

import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("/posts")
    fun getPosts() : Call<List<ResponseModel>>
}