package com.example.mapboxexample.di

import com.example.mapboxexample.BuildConfig
import com.example.mapboxexample.data.webservice.ApiClient
import com.example.mapboxexample.data.webservice.ApiHelper
import com.example.mapboxexample.data.webservice.ApiHelperImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module


val apiModule = module {

    single {
        ApiClient.getService(
            get(),
            get()
        )
    }
    single { OkHttpClient.Builder() }
    single { BuildConfig.BASE_URL }

    single<ApiHelper> {
        return@single ApiHelperImpl(
            get()
        )
    }
}