package com.example.ankit.newtask.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ankit on 14/3/18.
 */
data class User(

        @SerializedName("id")
        val id: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("user_name")
        val userName: String,

        @SerializedName("email_id")
        val emailId: String
)