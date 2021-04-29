package com.example.mapboxexample.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("glide")
    fun glide(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide
                .with(view)
                .load(url)
                .into(view)

        }
    }
}