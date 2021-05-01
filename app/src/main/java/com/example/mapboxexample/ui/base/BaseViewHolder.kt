package com.example.mapboxexample.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mapboxexample.common.ItemViewHolder

abstract class BaseViewHolder<model> constructor(view: View) : RecyclerView.ViewHolder(view),
    ItemViewHolder<model> {
    override fun onBind(item: model) {
        setListeners(item)
    }
    abstract fun setListeners(item: model)
}
