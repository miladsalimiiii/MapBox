package com.example.mapboxexample.di

import com.example.mapboxexample.ui.detail.DetailViewModel
import com.example.mapboxexample.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { MapViewModel(get(),get()) }
    viewModel { DetailViewModel() }
}