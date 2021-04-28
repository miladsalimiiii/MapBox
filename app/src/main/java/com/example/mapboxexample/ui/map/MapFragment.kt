package com.example.mapboxexample.ui.map

import androidx.lifecycle.Observer
import com.example.mapboxexample.R
import com.example.mapboxexample.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment() {

    private val mapViewModel:MapViewModel by viewModel()
    override fun initComponents() {
         mapViewModel.getPoints()
    }

    override fun initUiListeners() {

    }

    override fun initObservers() {
        mapViewModel.uiCommunicationListener.observe(viewLifecycleOwner, Observer {
            checkCommunicate(it,::retryFunction)
        })
    }

    private fun retryFunction(){}

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_map
    }
}