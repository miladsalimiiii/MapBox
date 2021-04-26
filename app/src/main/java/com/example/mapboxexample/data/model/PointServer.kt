package com.example.mapboxexample.data.model

import com.google.gson.annotations.SerializedName

data class PointServer(@SerializedName("latitude")val latitude:Long,
                       @SerializedName("longitude")val longitude:Long)