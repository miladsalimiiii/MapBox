package com.example.mapboxexample.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class PointServer(
    @SerializedName("id") val id: Long?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("icon") val image: String?,
    @SerializedName("locale") val locale: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("rate") val rate: Long?,
    @SerializedName("imageOne") val imageOne: String?,
    @SerializedName("imageTwo") val imageTwo: String?,
    @SerializedName("imageThree") val imageThree: String?,
    @SerializedName("imageFour") val imageFour: String?
)

