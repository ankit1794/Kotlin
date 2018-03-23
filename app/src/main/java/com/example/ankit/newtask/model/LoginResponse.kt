package com.example.ankit.newtask.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ankit on 22/3/18.
 */
class LoginResponse {

    @SerializedName("success")
    var isSuccess: String? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("user")
    var user: User? = null
}