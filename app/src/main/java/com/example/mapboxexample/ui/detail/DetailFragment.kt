package com.example.mapboxexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mapboxexample.databinding.FragmentDetailBinding
import com.example.mapboxexample.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var fragmentDetailBinding: FragmentDetailBinding

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

    }
}