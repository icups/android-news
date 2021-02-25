package com.news.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "article")
class ArticleEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @SerializedName("Content-Type") var contentType: String = "",
    @SerializedName("DeviceId") var deviceId: String = "",
    @SerializedName("DeviceName") var deviceName: String = "",
    @SerializedName("Version") var version: String = "",
    @SerializedName("TimeZone") var timeZone: String = "",
    @SerializedName("Os") var operatingSystem: String = "",
    @SerializedName("Long") var long: String = "",
    @SerializedName("Lat") var lat: String = "",
    @SerializedName("Firebase") var fireBase: String = ""
) : Parcelable