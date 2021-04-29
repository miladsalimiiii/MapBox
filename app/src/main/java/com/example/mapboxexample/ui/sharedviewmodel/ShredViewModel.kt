package com.example.mapboxexample.ui.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mapboxexample.common.UICommunication
import com.example.mapboxexample.data.model.PointServer
import com.example.mapboxexample.data.repository.PointRepository
import com.example.mapboxexample.data.webservice.ApiResponseHandler
import com.example.mapboxexample.data.webservice.ApiResult
import com.example.mapboxexample.ui.base.BaseViewModel
import com.example.mapboxexample.util.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShredViewModel(
    private val pointRepository: PointRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val _getPointsResponseLiveData = MutableLiveData<List<PointServer>>()
    val getPointsResponseLiveData: LiveData<List<PointServer>>
        get() = _getPointsResponseLiveData


    private val _getPointDetailResponseLiveData = MutableLiveData<PointServer>()
    val getPointDetailResponseLiveData: LiveData<PointServer>
        get() = _getPointDetailResponseLiveData

    var selectedPointPositionLiveData = MutableLiveData<Long>()

    private val _latitudeLiveData = MutableLiveData<String>()
    val latitudeLiveData: LiveData<String>
        get() = _latitudeLiveData

    private val _longitudeLiveData = MutableLiveData<String>()
    val longitudeLiveData: LiveData<String>
        get() = _longitudeLiveData

    private val _imageLiveData = MutableLiveData<String>()
    val imageLiveData: LiveData<String>
        get() = _imageLiveData


    fun getPoints() {
        object : ApiResponseHandler<List<PointServer>>(
            networkHelper = networkHelper,
            apiCall = {
                pointRepository.getAllPoints()
            }, viewModelScope = viewModelScope
        ) {
            override fun handleLoading(enable: Boolean) {
                viewModelScope.launch(Dispatchers.Main) {
                    _loadingLiveData.value = enable
                }
            }

            override fun handleSuccessResult(successResult: ApiResult.Success<List<PointServer>>) {
                _getPointsResponseLiveData.postValue(successResult.data)
            }

            override fun handleError(errorBody: Throwable?, uiCommunication: UICommunication) {
                _uiCommunicationListener.postValue(uiCommunication)
            }
        }.getResult()
    }

    fun getPointDetail(pointId: String) {
        object : ApiResponseHandler<PointServer>(
            networkHelper = networkHelper,
            apiCall = {
                pointRepository.getPoint(pointId = pointId)
            }, viewModelScope = viewModelScope
        ) {
            override fun handleLoading(enable: Boolean) {
                viewModelScope.launch(Dispatchers.Main) {
                    _loadingLiveData.value = enable
                }
            }

            override fun handleSuccessResult(successResult: ApiResult.Success<PointServer>) {
                _latitudeLiveData.postValue(successResult.data.latitude.toString())
                _longitudeLiveData.postValue(successResult.data.longitude.toString())
                _imageLiveData.postValue(successResult.data.image?:"")
            }

            override fun handleError(errorBody: Throwable?, uiCommunication: UICommunication) {
                _uiCommunicationListener.postValue(uiCommunication)
            }
        }.getResult()
    }
}