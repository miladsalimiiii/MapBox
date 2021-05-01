package com.example.mapboxexample.data.webservice

sealed class ApiResult {
    data class Success<T : Any>(val data: T) : ApiResult()

    data class GenericError(
        val code: Int?,
        val message: String? = null,
        val errorBody: Throwable? = null
    ) : ApiResult()

    object NetworkError : ApiResult()

    object LocalNetworkError : ApiResult()

    object NetworkConnectionException : ApiResult()
}