package com.example.ankit.newtask.model

import com.google.gson.annotations.SerializedName

/**
 * Created by dell on 14/3/18.
 */

class User(
        @field:SerializedName("name")
        var name: String?,
        @field:SerializedName("username")
        var username: String?,
        @field:SerializedName("email")
        var email: String?,
        @field:SerializedName("password")
        var password: String?) {


    @SerializedName("id")
    var id: String? = null
}
