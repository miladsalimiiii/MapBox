package com.example.mapboxexample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mapboxexample.ui.base.BaseViewModel

class DetailViewModel : BaseViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}