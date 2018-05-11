package com.example.ankit.newtask.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ankit on 14/3/18.
 */
class Login (

    @field:SerializedName("username")
    var username: String?,

    @field:SerializedName("password")
    var password: String?)

    /*fun login(username: String, password: String) {
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
    }*/


