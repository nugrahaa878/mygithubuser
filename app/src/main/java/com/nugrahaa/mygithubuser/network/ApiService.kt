package com.nugrahaa.mygithubuser.network

import com.nugrahaa.mygithubuser.model.GithubUser
import com.nugrahaa.mygithubuser.model.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getListByName(@Query("q") q: String): Call<ResponseUser>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<GithubUser>
}