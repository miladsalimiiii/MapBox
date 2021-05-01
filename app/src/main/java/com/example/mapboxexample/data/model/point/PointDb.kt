package com.example.mapboxexample.data.model.point

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mapboxexample.data.model.image.Image
import com.example.mapboxexample.data.model.image.ImageConvertor

@Entity(tableName = "point")
data class PointDb(
    @PrimaryKey()val id: Long?,
    @ColumnInfo(name = "latitude")val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "icon") val icon: String?,
    @ColumnInfo(name = "locale")val locale: String?,
    @ColumnInfo(name = "email" )val email: String?,
    @ColumnInfo(name = "password" )val password: String?,
    @ColumnInfo(name = "rate")val rate: Long?,
    @TypeConverters(ImageConvertor::class)@ColumnInfo(name = "imageList") val imageList: List<Image>?
)