package com.example.mapboxexample.data.repository

import com.example.mapboxexample.data.db.AppDataBase
import com.example.mapboxexample.data.model.PointDb
import com.example.mapboxexample.data.model.PointServer
import com.example.mapboxexample.data.webservice.ApiHelper

class PointRepository(private val apiHelper: ApiHelper, private val appDataBase: AppDataBase) {

    suspend fun getAllPoints() = apiHelper.getAllPoints()

    suspend fun getPoint(pointId: String) = apiHelper.getPoint(pointId)

    suspend fun insertAllPointsToDb(pointList: List<PointDb>) =
        appDataBase.pointDao().insertPointList(pointList)

    suspend fun getAllPointsFromDb() = appDataBase.pointDao().getPointList()

    suspend fun getPointFromDb(pointId: String) = appDataBase.pointDao().getPoint(pointId)
}