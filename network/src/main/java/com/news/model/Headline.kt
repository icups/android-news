package com.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Headline(val main: String = "") : Parcelable