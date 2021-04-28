package com.example.mapboxexample.di

import com.example.mapboxexample.data.repository.PointRepository
import org.koin.dsl.module

val repositoryModule= module {
    single { PointRepository(get()) }
}