package com.news.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PaginatedResponse<T>(
    val status: String = "",
    @SerializedName("num_results") val total: Int = 0,
    @SerializedName("results") val result: List<T> = ArrayList()
) : Serializable