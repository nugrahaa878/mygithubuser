package com.nugrahaa.mygithubuser.network

import com.nugrahaa.mygithubuser.model.GithubUser
import com.nugrahaa.mygithubuser.model.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getListByName(@Header("Authorization")  authorization: String ,@Query("q") q: String?): Call<ResponseUser>

    @GET("users/{username}")
    fun getUserDetail(@Header("Authorization")  authorization: String , @Path("username") username: String): Call<GithubUser>

    @GET("users/{username}/{follow}")
    fun getFollow(@Header("Authorization")  authorization: String , @Path("username") username: String, @Path("follow") follow: String): Call<ArrayList<GithubUser>>
}