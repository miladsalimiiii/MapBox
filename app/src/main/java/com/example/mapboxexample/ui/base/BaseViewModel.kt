package com.example.mapboxexample.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapboxexample.common.UICommunication

open class BaseViewModel:ViewModel() {

    protected val _uiCommunicationListener = MutableLiveData<UICommunication>()
    val uiCommunicationListener: LiveData<UICommunication>
        get() = _uiCommunicationListener
}