package com.example.mapboxexample.util

import com.example.mapboxexample.data.model.point.PointDb
import com.example.mapboxexample.data.model.point.PointServer

class MapperUtil {

    fun pointDbToPointServer(pointServer: PointDb) = PointServer(
        pointServer.id,
        pointServer.latitude,
        pointServer.longitude,
        pointServer.icon,
        pointServer.locale,
        pointServer.email,
        pointServer.password,
        pointServer.rate,
        pointServer.imageList
    )
}