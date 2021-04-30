package com.example.mapboxexample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "point")
data class PointDb(
    @PrimaryKey()val id: Long?,
    @ColumnInfo(name = "latitude")val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "icon") val image: String?,
    @ColumnInfo(name = "locale")val locale: String?,
    @ColumnInfo(name = "email" )val email: String?,
    @ColumnInfo(name = "password" )val password: String?,
    @ColumnInfo(name = "rate")val rate: Long?,
    @ColumnInfo(name = "imageOne") val imageOne: String?,
    @ColumnInfo(name = "imageTwo")val imageTwo: String?,
    @ColumnInfo(name = "imageThree") val imageThree: String?,
    @ColumnInfo(name = "imageFour")val imageFour: String?
)