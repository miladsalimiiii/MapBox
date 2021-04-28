package com.example.mapboxexample.data.webservice

import com.example.mapboxexample.R
import com.example.mapboxexample.common.exception.NetworkConnectionException
import com.example.mapboxexample.util.NetworkHelper
import retrofit2.HttpException
import java.io.IOException


suspend fun <T : Any> safeApi(
    networkHelper: NetworkHelper,
    apiCall: suspend () -> T
): ApiResult = try {
    if (!networkHelper.isNetworkConnected())
        throw NetworkConnectionException()
    else
        ApiResult.Success(apiCall.invoke())
} catch (throwable: Throwable) {
    when (throwable) {
        is HttpException -> {
            val data = try {
                throwable
            } catch (e: Throwable) {
                null
            }
            ApiResult.GenericError(code = throwable.code(), errorBody = data)
        }
        is IOException ->
            ApiResult.NetworkError
        is NetworkConnectionException ->
            ApiResult.LocalNetworkError
        else -> {
            ApiResult.GenericError(
                code = null,
                message = R.string.network_unknown_error.toString()
            )
        }
    }
}
