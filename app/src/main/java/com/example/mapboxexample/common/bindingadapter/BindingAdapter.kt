package com.example.mapboxexample.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mapboxexample.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("glide")
    fun glide(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(view)
                .load(url)
                .apply(RequestOptions.placeholderOf(R.drawable.icon_placeholder))
                .into(view)

        }
    }
}