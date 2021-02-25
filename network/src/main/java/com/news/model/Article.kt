package com.news.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("_id") val id: String = "",
    val headline: Headline? = null,
    val source: String = "",
    val snippet: String = "",
    val multimedia: List<Multimedia> = emptyList(),
    @SerializedName("lead_paragraph") val content: String = "",
    @SerializedName("web_url") val url: String = "",
    @SerializedName("pub_date") val publishedAt: String = ""
) : Parcelable