package com.example.mapboxexample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mapboxexample.data.db.dao.PointDao
import com.example.mapboxexample.data.model.PointDb
import com.example.mapboxexample.data.model.PointServer

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