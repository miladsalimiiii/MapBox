package com.example.mapboxexample.di

import com.example.mapboxexample.ui.map.MapModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel {
        MapModel(
            get(),
            get()
        )
    }
}