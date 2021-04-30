package com.example.mapboxexample.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mapboxexample.common.UICommunication
import com.example.mapboxexample.data.model.PointDb
import com.example.mapboxexample.data.model.PointServer
import com.example.mapboxexample.data.repository.PointRepository
import com.example.mapboxexample.data.webservice.ApiResponseHandler
import com.example.mapboxexample.data.webservice.ApiResult
import com.example.mapboxexample.ui.base.BaseViewModel
import com.example.mapboxexample.util.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapModel(
    private val pointRepository: PointRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val _getPointsResponseLiveData = MutableLiveData<List<PointServer>>()
    val getPointsResponseLiveData: LiveData<List<PointServer>>
        get() = _getPointsResponseLiveData

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
                viewModelScope.launch(Dispatchers.IO) {
                    insertPointToDb(successResult.data)
                }
            }

            override fun handleError(errorBody: Throwable?, uiCommunication: UICommunication) {
                _uiCommunicationListener.postValue(uiCommunication)
                viewModelScope.launch(Dispatchers.IO) {
                    getPointListFromDb()
                }

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
                viewModelScope.launch(Dispatchers.IO) { getPoint(pointId)}
            }
        }.getResult()
    }

   suspend fun insertPointToDb(pointList:List<PointServer>){
         viewModelScope.launch(Dispatchers.IO) {

            val mapPointServerToPointDb= pointList.map {
                 PointDb(it.id,it.latitude,it.longitude,it.image,it.locale)
             }
             pointRepository.insertAllPointsToDb(mapPointServerToPointDb).let {
                 _getPointsResponseLiveData.postValue(pointList)
             }
         }
    }

    suspend fun getPointListFromDb(){
        pointRepository.getAllPointsFromDb().let { dbPointList ->
           val serverPointList= dbPointList.map {
                PointServer(it.id,it.latitude,it.longitude,it.image,it.locale)
            }
            _getPointsResponseLiveData.postValue(serverPointList)
        }
    }

    suspend fun getPoint(pointId:String){
        viewModelScope.launch(Dispatchers.IO) {
            pointRepository.getPointFromDb(pointId = pointId).let {
                _latitudeLiveData.postValue(it.latitude.toString())
                _longitudeLiveData.postValue(it.longitude.toString())
                _imageLiveData.postValue(it.image?:"")
            }
        }
    }
}