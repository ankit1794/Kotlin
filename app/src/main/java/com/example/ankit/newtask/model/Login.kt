package com.example.ankit.newtask.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ankit on 14/3/18.
 */
class Login(toString: Any, toString1: Any) {

    @SerializedName("username")
    private var username: String? = null

    @SerializedName("password")
    private var password: String? = null

    fun Login(username: String, password: String){
        this.username = username
        this.password = password
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }



}