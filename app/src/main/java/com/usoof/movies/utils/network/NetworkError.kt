package com.usoof.movies.utils.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkError(

    @Expose
    @SerializedName("status_code")
    val status_code: Int = -1,

    @Expose
    @SerializedName("message")
    val message: String = "Something went wrong",

    val success: Boolean = false


)
//Invalid API key: You must be granted a valid key.