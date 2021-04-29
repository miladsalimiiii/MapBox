package com.example.mapboxexample.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class PointServer(
    @SerializedName("id") val id: Long?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("image") val image: String?,
    @SerializedName("locale") val locale: String?
)