package com.example.mapboxexample.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mapboxexample.ui.BaseViewModel

class MapViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}