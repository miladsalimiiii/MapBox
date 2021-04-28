package com.example.mapboxexample.ui.map

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mapboxexample.R
import com.example.mapboxexample.data.model.PointServer
import com.example.mapboxexample.databinding.FragmentMapBinding
import com.example.mapboxexample.ui.base.BaseFragment
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val SOURCE_ID = "SOURCE_ID"
private const val ICON_ID = "ICON_ID"
private const val LAYER_ID = "LAYER_ID"

class MapFragment : BaseFragment(), OnMapReadyCallback {

    private val mapViewModel: MapViewModel by viewModel()
    private lateinit var fragmentInvestmentBinding: FragmentMapBinding
    private val symbolLayerIconFeatureList: MutableList<Feature> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentInvestmentBinding =
            FragmentMapBinding.inflate(inflater, container, false)
        fragmentInvestmentBinding.mapViewModel = mapViewModel
        fragmentInvestmentBinding.lifecycleOwner = this
        return fragmentInvestmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInvestmentBinding.mapView.apply {
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

    private fun retryFunction() {mapViewModel.getPoints()}
    private fun addPointsToMap(pointList:List<PointServer>){
        for (point in pointList)
            symbolLayerIconFeatureList.add(
                Feature.fromGeometry(
                    point.longitude?.let { point.latitude?.let { latitude -> Point.fromLngLat(it, latitude) } }
                )
            )
        fragmentInvestmentBinding.mapView.getMapAsync(this)
    }
    override fun onMapReady(mapboxMap: MapboxMap) {

        mapboxMap.setStyle(
            Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41")

        .withImage(ICON_ID, BitmapFactory.decodeResource(
            resources, R.drawable.mapbox_marker_icon_default))

                .withSource(
                    GeoJsonSource(SOURCE_ID,
                    FeatureCollection.fromFeatures(symbolLayerIconFeatureList))
                )

                .withLayer(SymbolLayer(LAYER_ID, SOURCE_ID)

                    .withProperties(
                        iconImage(ICON_ID),
                        iconAllowOverlap(true),
                        iconIgnorePlacement(true)
                    )
                )
        )
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentInvestmentBinding.mapView.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        fragmentInvestmentBinding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        fragmentInvestmentBinding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        fragmentInvestmentBinding.mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        fragmentInvestmentBinding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        fragmentInvestmentBinding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentInvestmentBinding.mapView.onDestroy()
    }
}