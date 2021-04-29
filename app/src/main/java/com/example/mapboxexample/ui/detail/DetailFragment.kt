package com.example.mapboxexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mapboxexample.databinding.FragmentDetailBinding
import com.example.mapboxexample.ui.base.BaseFragment
import com.example.mapboxexample.ui.sharedviewmodel.ShredViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment() {

    private val detailViewModel: ShredViewModel by sharedViewModel()
    private lateinit var fragmentDetailBinding: FragmentDetailBinding
    private var pointId: Long? = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailBinding =
            FragmentDetailBinding.inflate(inflater, container, false)
        fragmentDetailBinding.detailViewModel = detailViewModel
        fragmentDetailBinding.lifecycleOwner = this
        return fragmentDetailBinding.root
    }

    override fun initComponents() {

    }

    override fun initUiListeners() {

    }

    override fun initObservers() {
        detailViewModel.selectedPointPositionLiveData.observe(viewLifecycleOwner, Observer {
            detailViewModel.getPointDetail(it.toString())
        })
    }
}