package com.example.mapboxexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mapboxexample.databinding.FragmentDetailBinding
import com.example.mapboxexample.ui.base.BaseFragment
import com.example.mapboxexample.ui.map.MapViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val detailViewModel: MapViewModel by sharedViewModel()
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
          //   detailViewModel.getPointDetail()
    }

    override fun initUiListeners() {

    }

    override fun initObservers() {
//        sharedViewModel.selectedPointPositionLiveData.observe(viewLifecycleOwner, Observer {
//            pointId = it
//        })
//        detailViewModel.getPointDetailResponseLiveData.observe(viewLifecycleOwner, Observer {
//
//        })
    }
}