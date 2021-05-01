package com.example.mapboxexample.data.model.point

import com.example.mapboxexample.data.model.image.Image
import com.google.gson.annotations.SerializedName

data class PointServer(
    @SerializedName("id") val id: Long?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("locale") val locale: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("rate") val rate: Long?,
    @SerializedName("imageList") val imageList: List<Image>?
)

