package com.example.ankit.newtask.retrofit

import com.example.ankit.newtask.model.Login
import com.example.ankit.newtask.model.LoginResponse
import com.example.ankit.newtask.model.User
import com.example.ankit.newtask.model.UserApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by ankit on 22/3/18.
 */
interface APIService {

    @POST("user/register")
    fun registerUser(@Body user: User): Call<UserApiResponse>

    @POST("user/authenticate")
    fun authenticateUser(@Body login: Login): Call<LoginResponse>

    @GET("user/profile")
    fun getProfile(@Header("Authorization") authToken: String): Call<LoginResponse>
}