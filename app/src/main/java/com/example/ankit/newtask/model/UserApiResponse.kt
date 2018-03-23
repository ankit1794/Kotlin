package com.example.ankit.newtask.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ankit on 22/3/18.
 */
class UserApiResponse(@field:SerializedName("success")
                      var isSuccess: Boolean, @field:SerializedName("msg")
                      var message: String?)