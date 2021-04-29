package com.example.mapboxexample.ui.map

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

class MapViewModel(
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



    fun getPoints(){
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

    fun getPointDetail() {
        selectedPointPositionLiveData.value?.let {
            object : ApiResponseHandler<PointServer>(
                networkHelper = networkHelper,
                apiCall = {
                    pointRepository.getPoint(pointId = it.toString())
                }, viewModelScope = viewModelScope
            ) {
                override fun handleLoading(enable: Boolean) {
                    viewModelScope.launch(Dispatchers.Main) {
                        _loadingLiveData.value = enable
                    }
                }

                override fun handleSuccessResult(successResult: ApiResult.Success<PointServer>) {
                    _getPointDetailResponseLiveData.postValue(successResult.data)
                }

                override fun handleError(errorBody: Throwable?, uiCommunication: UICommunication) {
                    _uiCommunicationListener.postValue(uiCommunication)
                }
            }.getResult()
        }
    }
}