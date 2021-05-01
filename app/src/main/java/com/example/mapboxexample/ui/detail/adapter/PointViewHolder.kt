package com.example.mapboxexample.ui.detail.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.mapboxexample.data.model.image.Image
import com.example.mapboxexample.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_imagegridview.view.*
import org.koin.core.KoinComponent

class PointViewHolder(
    override val mView: View
) : BaseViewHolder<Image>(mView), KoinComponent {

    override fun onBind(item: Image) {
        super.onBind(item)

        Glide.with(mView)
            .load(item.image)
            .centerCrop()
            .into(mView.imageViewItem)
    }

    override fun setListeners(item: Image) {

    }
}