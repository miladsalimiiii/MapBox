package com.example.mapboxexample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mapboxexample.common.UICommunication
import com.example.mapboxexample.data.model.point.PointServer
import com.example.mapboxexample.data.repository.PointRepository
import com.example.mapboxexample.data.webservice.ApiResponseHandler
import com.example.mapboxexample.data.webservice.ApiResult
import com.example.mapboxexample.ui.base.BaseViewModel
import com.example.mapboxexample.util.MapperUtil
import com.example.mapboxexample.util.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailViewModel(
    private val pointRepository: PointRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel(),KoinComponent {

    private val mapperUtil : MapperUtil by inject()

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    private val _pointDetailLiveData = MutableLiveData<PointServer>()
    val pointDetailLiveData: LiveData<PointServer>
        get() = _pointDetailLiveData

    private val _latitudeLiveData = MutableLiveData<String>()
    val latitudeLiveData: LiveData<String>
        get() = _latitudeLiveData

    private val _longitudeLiveData = MutableLiveData<String>()
    val longitudeLiveData: LiveData<String>
        get() = _longitudeLiveData

    private val _imageLiveData = MutableLiveData<String>()
    val imageLiveData: LiveData<String>
        get() = _imageLiveData

    private val _localeLiveData = MutableLiveData<String>()
    val localeLiveData: LiveData<String>
        get() = _localeLiveData

    private val _usernameLiveData = MutableLiveData<String>()
    val usernameLiveData: LiveData<String>
        get() = _usernameLiveData

    private val _passwordLiveData = MutableLiveData<String>()
    val passwordLiveData: LiveData<String>
        get() = _passwordLiveData

    private val _progressLiveData = MutableLiveData<Int>()
    val progressLiveData: LiveData<Int>
        get() = _progressLiveData

    private val _percentLiveData = MutableLiveData<String>()
    val percentLiveData: LiveData<String>
        get() = _percentLiveData

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
                successResult.data.apply {
                    _pointDetailLiveData.postValue(this)
                    _latitudeLiveData.postValue(latitude.toString())
                    _longitudeLiveData.postValue(longitude.toString())
                    _imageLiveData.postValue(icon ?: "")
                    _progressLiveData.postValue(rate?.toInt() ?: 0)
                    _localeLiveData.postValue(locale ?: "teh")
                    _usernameLiveData.postValue(email ?: "")
                    _passwordLiveData.postValue(password ?: "")
                    _percentLiveData.postValue(rate.toString() ?: "")
                }
            }

            override fun handleError(errorBody: Throwable?, uiCommunication: UICommunication) {
                _uiCommunicationListener.postValue(uiCommunication)
                viewModelScope.launch(Dispatchers.IO) { getPoint(pointId) }
            }
        }.getResult()
    }

    suspend fun getPoint(pointId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pointRepository.getPointFromDb(pointId = pointId).let {

                _pointDetailLiveData.postValue(mapperUtil.pointDbToPointServer(it))
                _latitudeLiveData.postValue(it.latitude.toString())
                _longitudeLiveData.postValue(it.longitude.toString())
                _imageLiveData.postValue(it.icon ?: "")
                _progressLiveData.postValue(it.rate?.toInt() ?: 0)
                _localeLiveData.postValue(it.locale ?: "teh")
                _usernameLiveData.postValue(it.email ?: "")
                _passwordLiveData.postValue(it.password ?: "")
                _percentLiveData.postValue(it.rate.toString() ?: "")
            }
        }
    }
}