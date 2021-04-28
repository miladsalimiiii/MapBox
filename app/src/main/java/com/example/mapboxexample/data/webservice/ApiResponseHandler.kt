package com.example.mapboxexample.data.webservice

import com.example.mapboxexample.R
import com.example.mapboxexample.common.UICommunication
import com.example.mapboxexample.util.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class ApiResponseHandler<NetworkObject : Any> constructor(
    private val networkHelper: NetworkHelper,
    private val apiCall: suspend () -> NetworkObject,
    private val viewModelScope: CoroutineScope
) {
    fun getResult() {
        viewModelScope.launch(Dispatchers.IO) {
            handleLoading(true)
            when (val result = safeApi(networkHelper, apiCall = apiCall)) {
                is ApiResult.Success<*> ->
                    handleSuccessResult(result as ApiResult.Success<NetworkObject>)

                is ApiResult.GenericError -> {
                    handleError(
                        result.errorBody,
                        UICommunication.ShowSnackbarWithRetry(message = R.string.server_error.toString())
                    )
                }
                is ApiResult.LocalNetworkError -> handleError(
                    uiCommunication = UICommunication.ShowSnackbarWithRetry(
                        message = R.string.error_internet_connection.toString()
                    )
                )
                is ApiResult.NetworkError -> handleError(
                    uiCommunication = UICommunication.ShowSnackbarWithRetry(
                        message = R.string.server_error.toString()
                    )
                )
                is ApiResult.NetworkConnectionException -> handleError(
                    uiCommunication = UICommunication.ShowSnackbarWithRetry(
                        message = R.string.server_error.toString()
                    )
                )
            }
            handleLoading(false)
        }
    }

    abstract fun handleLoading(enable: Boolean)
    abstract fun handleSuccessResult(successResult: ApiResult.Success<NetworkObject>)
    abstract fun handleError(errorBody: Throwable? = null, uiCommunication: UICommunication)
}