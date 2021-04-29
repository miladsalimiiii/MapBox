package com.example.mapboxexample.di

import com.example.mapboxexample.ui.sharedviewmodel.ShredViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel {
        ShredViewModel(
            get(),
            get()
        )
    }
}