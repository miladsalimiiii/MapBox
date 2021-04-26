package com.example.mapboxexample.ui.detail

import com.example.mapboxexample.R
import com.example.mapboxexample.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    override fun initComponents() {

    }

    override fun initUiListeners() {

    }

    override fun initObservers() {

    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_detail
    }

}