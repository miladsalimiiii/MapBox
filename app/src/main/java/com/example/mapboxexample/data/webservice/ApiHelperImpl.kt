package com.example.mapboxexample.data.webservice

import com.example.mapboxexample.data.model.point.PointServer

class ApiHelperImpl(private val apiService: ApiService):ApiHelper {

    override suspend fun getAllPoints(): List<PointServer> = apiService.getAllPoints()

    override suspend fun getPoint(id: String) = apiService.getPoint(id = id)
}