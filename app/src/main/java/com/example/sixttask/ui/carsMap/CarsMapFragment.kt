package com.example.sixttask.ui.carsMap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.sixttask.R
import com.example.sixttask.data.json.Car
import com.example.sixttask.ui.carsMap.marker.CustomMarkerInfoWindow
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel


class CarsMapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private val model: CarsMapViewModel by viewModel()
    private lateinit var mMap: GoogleMap
    private var cars: List<Car>? = null
    private var isMapReady = false


    companion object {
        fun newInstance() = CarsMapFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cars_map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        model.carsUIModel.observe(this, Observer {
            when {
                it.isSuccess -> {
                    cars = it.cars!!
                    addCarsMarkers()
                }
            }
        })
        model.getCars()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        isMapReady = true
        addCarsMarkers()
        mMap.setOnMarkerClickListener(this)
    }

    private fun addCarsMarkers() {
        if (!cars.isNullOrEmpty() && isMapReady)
            cars?.forEachIndexed { index, it ->
                val carMarker = LatLng(it.latitude, it.longitude)
                val marker =
                    mMap.addMarker(MarkerOptions().position(carMarker).title("${it.make} ${it.series}"))
                marker.tag = it
                if (index == 0) {
                    addInfoWindow(marker)
                }

            }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        addInfoWindow(marker!!)
        return true
    }

    private fun addInfoWindow(marker: Marker) {
        val customMarkerInfoWindow =
            CustomMarkerInfoWindow(
                activity!!,
                marker.tag as Car
            )
        mMap.setInfoWindowAdapter(customMarkerInfoWindow)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 12.0f))
        marker.showInfoWindow()
    }

}
