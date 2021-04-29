package com.example.mapboxexample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "point")
data class PointDb(
    @PrimaryKey()val id: Long?,
    @ColumnInfo(name = "latitude")val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "locale")val locale: String?
)