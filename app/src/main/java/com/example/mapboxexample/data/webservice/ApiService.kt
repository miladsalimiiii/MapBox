package com.example.mapboxexample.data.webservice

import com.example.mapboxexample.data.model.point.PointServer
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v1/points")
    suspend fun getAllPoints():List<PointServer>

    @GET("api/v1/points/{id}")
    suspend fun getPoint(@Path("id")id:String): PointServer
}