package com.example.mapboxexample.ui.map

import com.example.mapboxexample.R
import com.example.mapboxexample.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment() {

    private val mapViewModel:MapViewModel by viewModel()
    override fun initComponents() {

    }

    override fun initUiListeners() {

    }

    override fun initObservers() {

    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_map
    }
}