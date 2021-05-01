package com.example.mapboxexample.common

import android.view.View

interface ItemViewHolder<model> {
    val mView: View
    fun onBind(item: model)
}