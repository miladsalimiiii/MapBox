package com.example.mapboxexample.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDataBase : RoomDatabase() {

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