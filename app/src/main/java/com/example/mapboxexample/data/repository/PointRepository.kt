package com.example.mapboxexample.data.repository

import com.example.mapboxexample.data.webservice.ApiHelper

class PointRepository(private val apiHelper: ApiHelper) {

    suspend fun getAllPoints() = apiHelper.getAllPoints()

    suspend fun getPoint(pointId:String) = apiHelper.getPoint(pointId)
}