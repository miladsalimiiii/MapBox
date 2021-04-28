package com.example.mapboxexample.data.model

import com.google.gson.annotations.SerializedName

data class PointServer(
    @SerializedName("id") val id: Long?,
    @SerializedName("latitude") val latitude: Float?,
    @SerializedName("longitude") val longitude: Float?
)