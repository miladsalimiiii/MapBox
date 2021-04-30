package com.example.mapboxexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.mapboxexample.R
import com.example.mapboxexample.databinding.FragmentDetailBinding
import com.example.mapboxexample.ui.base.BaseFragment
import com.example.mapboxexample.ui.map.MapModel
import com.google.android.material.appbar.AppBarLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment() {

    private val detailViewModel: MapModel by sharedViewModel()
    private lateinit var fragmentDetailBinding: FragmentDetailBinding
    private val navArgs:DetailFragmentArgs by navArgs()

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
        detailViewModel.getPointDetail(navArgs.pointId)
    }

    override fun initUiListeners() {
        fragmentDetailBinding.appBar.addOnOffsetChangedListener(object :
            AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true

                } else if (isShow) {
                    isShow = false

                }
            }
        })
    }

    override fun initObservers() {
        detailViewModel.selectedPointPositionLiveData.observe(viewLifecycleOwner, Observer {

        })

        detailViewModel.uiCommunicationListener.observe(viewLifecycleOwner, Observer {
            checkCommunicate(it, ::retryFunction)
        })
    }

    private fun retryFunction() {
        detailViewModel.getPoints()
    }
}