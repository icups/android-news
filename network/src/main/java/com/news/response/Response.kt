package com.news.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Response<T>(
    val status: String = "",
    @SerializedName("response") val result: T
) : Serializable