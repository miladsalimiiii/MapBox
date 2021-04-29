package com.example.mapboxexample.di

import com.example.mapboxexample.data.db.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.math.sin

val databaseModule = module {
    single { AppDataBase.getInstance(androidContext())}
    single { get<AppDataBase>().pointDao() }
}