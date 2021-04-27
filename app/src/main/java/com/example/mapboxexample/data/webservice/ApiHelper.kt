package com.example.mapboxexample.data.webservice

import com.example.mapboxexample.data.model.PointServer
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHelper {

    suspend fun getAllPoints():List<PointServer>

    suspend fun getPoint(@Path("id")id:String)
}