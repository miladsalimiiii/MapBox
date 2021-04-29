package com.example.mapboxexample.di

import com.example.mapboxexample.data.repository.PointRepository
import org.koin.dsl.module
import kotlin.math.sin

val repositoryModule= module {
    single { PointRepository(get(),get()) }
}