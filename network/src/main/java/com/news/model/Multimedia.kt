package com.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Multimedia(
    val url: String = "",
    val type: String = "",
    val subtype: String = ""
) : Parcelable