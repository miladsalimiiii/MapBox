package com.example.mapboxexample.di

import com.example.mapboxexample.util.MapperUtil
import com.example.mapboxexample.util.NetworkHelper
import com.example.mapboxexample.util.SnackbarUtil
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilModule= module {
    single { NetworkHelper(androidContext()) }
    single { SnackbarUtil(androidContext()) }
    single { MapperUtil() }
}