package com.example.mapboxexample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mapboxexample.R
import com.example.mapboxexample.common.UICommunication
import com.example.mapboxexample.util.SnackbarUtil
import org.koin.android.ext.android.inject

abstract class BaseFragment: Fragment() {

    private val snackbarUtil:SnackbarUtil by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initUiListeners()
        initObservers()
    }

    protected abstract fun initComponents()
    protected abstract fun initUiListeners()
    protected abstract fun initObservers()

    fun checkCommunicate(
        uICommunication: UICommunication,
        retryClickListener: (() -> Unit?)? =null
    ) {
        when (uICommunication) {
            is UICommunication.ShowSnackbar -> {
                snackbarUtil.showSnackbarNotify(
                    view = requireView(),
                    string = uICommunication.message.toIntOrNull()?.let { getString(it) }
                        ?: uICommunication.message
                )
            }
            is UICommunication.ShowSnackbarWithRetry -> {
                snackbarUtil.showSnackbarNotify(
                    view = requireView(),
                    string = uICommunication.message.toIntOrNull()?.let { getString(it) }
                        ?: uICommunication.message, snackbarLength = -2,anchorView = requireActivity().findViewById(R.id.nav_view))
                    .setAction(getString(R.string.retry_again)) {
                        retryClickListener?.invoke()
                    }
            }
        }
    }
}