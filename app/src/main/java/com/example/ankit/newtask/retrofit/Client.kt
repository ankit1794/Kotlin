package com.example.ankit.newtask.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by ankit on 14/3/18.
 */
object Client {
    // for real device 192.168.0.108
    val BASE_URL = "http://192.168.0.108:3000/"
    var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            return retrofit
        }
}