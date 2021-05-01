package com.example.mapboxexample.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mapboxexample.data.model.point.PointDb

@Dao
interface PointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPointList(pointList: List<PointDb>)

    @Query("SELECT * FROM point")
    suspend fun getPointList(): List<PointDb>

    @Query("SELECT * FROM point WHERE id=:pointId")
    suspend fun getPoint(pointId: String): PointDb
}