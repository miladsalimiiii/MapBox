package com.example.mapboxexample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapboxexample.databinding.FragmentDetailBinding
import com.example.mapboxexample.ui.base.BaseFragment
import com.example.mapboxexample.ui.detail.adapter.ImageListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var fragmentDetailBinding: FragmentDetailBinding
    private val navArgs:DetailFragmentArgs by navArgs()
    private lateinit var imageListAdapter: ImageListAdapter

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
        setUpRecyclerView()
        detailViewModel.getPointDetail(navArgs.pointId)
    }

    override fun initUiListeners() {

    }

    override fun initObservers() {
        detailViewModel.pointDetailLiveData.observe(viewLifecycleOwner, Observer {
            imageListAdapter.differ.submitList(it.imageList)
        })
        detailViewModel.uiCommunicationListener.observe(viewLifecycleOwner, Observer {
            checkCommunicate(it, ::retryFunction)
        })
    }

    private fun retryFunction() {
        detailViewModel.getPointDetail(navArgs.pointId)
    }

    private fun setUpRecyclerView() {
        imageListAdapter = ImageListAdapter()
        fragmentDetailBinding.imageListGridView.apply {
            adapter = imageListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }
    }
}