package com.example.mapboxexample.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mapboxexample.R
import com.example.mapboxexample.data.model.PointServer
import com.example.mapboxexample.databinding.FragmentMapBinding
import com.example.mapboxexample.ui.base.BaseFragment
import com.example.mapboxexample.util.SnackbarUtil
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val MAKI_ICON_HARBOR = "harbor-15"
private const val MAKI_ICON_CAFE = "cafe-15"

class MapFragment : BaseFragment(), OnMapReadyCallback {

    private val mapViewModel: MapViewModel by sharedViewModel()
    private lateinit var fragmentMapBinding: FragmentMapBinding
    private val symbolLayerIconFeatureList: MutableList<SymbolOptions> = ArrayList()
    private lateinit var symbolManager: SymbolManager
    private val snackbarUtil: SnackbarUtil by inject()

    companion object {
        private var SYMBOL_LAST_CLICKED = 0L
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMapBinding =
            FragmentMapBinding.inflate(inflater, container, false)
        fragmentMapBinding.mapViewModel = mapViewModel
        fragmentMapBinding.lifecycleOwner = this
        return fragmentMapBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMapBinding.mapView.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }

    }

    override fun initComponents() {
        mapViewModel.getPoints()
    }

    override fun initUiListeners() {

    }

    override fun initObservers() {
        mapViewModel.uiCommunicationListener.observe(viewLifecycleOwner, Observer {
            checkCommunicate(it, ::retryFunction)
        })
        mapViewModel.getPointsResponseLiveData.observe(viewLifecycleOwner, Observer {
            addPointsToMap(it)
        })
    }

    private fun retryFunction() {
        mapViewModel.getPoints()
    }

    private fun addPointsToMap(pointList: List<PointServer>) {
        for (point in pointList)
            symbolLayerIconFeatureList.add(
                SymbolOptions()
                    .withLatLng(point.latitude?.let {
                        point.longitude?.let { latitude ->
                            LatLng(
                                it,
                                latitude
                            )
                        }
                    })
                    .withIconImage(MAKI_ICON_HARBOR)
                    .withIconSize(2.0f)
            )
        fragmentMapBinding.mapView.getMapAsync(this)
    }

    override fun onMapReady(mapboxMap: MapboxMap) {

        mapboxMap.setStyle(Style.LIGHT) { style ->
            symbolManager = SymbolManager(fragmentMapBinding.mapView, mapboxMap, style)

            val symbolList = symbolManager.create(symbolLayerIconFeatureList)

            symbolManager.addClickListener { symbol ->
                snackbarUtil.showSnackbarNotify(
                    requireView(), "Symbol clicked", requireActivity().findViewById(
                        R.id.nav_view
                    )
                )

                symbolList[SYMBOL_LAST_CLICKED.toInt()].iconImage = MAKI_ICON_HARBOR
                symbol.apply {
                    SYMBOL_LAST_CLICKED = id.also {
                        mapViewModel.selectedPointPositionLiveData.value=it
                    }
                    iconImage = MAKI_ICON_CAFE
                    symbolManager.update(this)
                }

                true
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentMapBinding.mapView.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        fragmentMapBinding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        fragmentMapBinding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        fragmentMapBinding.mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        fragmentMapBinding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        fragmentMapBinding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentMapBinding.mapView.onDestroy()
    }
}