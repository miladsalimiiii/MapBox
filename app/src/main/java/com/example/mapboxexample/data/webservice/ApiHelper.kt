package com.example.mapboxexample.data.webservice

import com.example.mapboxexample.data.model.point.PointServer
import retrofit2.http.Path

interface ApiHelper {

    suspend fun getAllPoints():List<PointServer>

    suspend fun getPoint(@Path("id")id:String): PointServer
}