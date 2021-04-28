package com.example.mapboxexample.common

sealed class UICommunication {
    data class ShowSnackbar(val message: String) : UICommunication()
    data class ShowSnackbarWithRetry(val message: String) : UICommunication()
}