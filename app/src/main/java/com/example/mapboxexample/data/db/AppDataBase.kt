package com.example.mapboxexample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mapboxexample.data.db.dao.PointDao
import com.example.mapboxexample.data.model.image.ImageConvertor
import com.example.mapboxexample.data.model.point.PointDb

@TypeConverters(ImageConvertor::class)
@Database(entities = [PointDb::class],version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pointDao(): PointDao

    companion object {
        private val instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase = instance
            ?: synchronized(this) {
            buildDataBase(
                context
            ) as AppDataBase
        }

        private fun buildDataBase(context: Context): RoomDatabase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "MapBox")
                .build()
        }
    }
}