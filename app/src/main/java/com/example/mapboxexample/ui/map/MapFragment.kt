package com.example.mapboxexample.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mapboxexample.R

class MapFragment : Fragment() {

    private lateinit var mapModel: MapModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mapModel =
                ViewModelProvider(this).get(MapModel::class.java)
        val root = inflater.inflate(R.layout.fragment_map, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        mapModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}