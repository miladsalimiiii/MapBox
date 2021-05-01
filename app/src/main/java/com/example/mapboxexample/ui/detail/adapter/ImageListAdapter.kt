package com.example.mapboxexample.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mapboxexample.R
import com.example.mapboxexample.data.model.image.Image
import com.example.mapboxexample.ui.base.BaseViewHolder


class ImageListAdapter() :
    RecyclerView.Adapter<BaseViewHolder<Image>>() {

    private val differCallback = object : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Image> {
        return PointViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_imagegridview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Image>, position: Int) {
        (holder as PointViewHolder).onBind(differ.currentList[position])
    }
}