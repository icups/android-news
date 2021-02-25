package com.news.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val message: String = "",
    @SerializedName("documentation_url") val url: String = ""
) : Parcelable