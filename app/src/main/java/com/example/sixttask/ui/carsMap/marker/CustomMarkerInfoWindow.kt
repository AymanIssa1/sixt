package com.example.sixttask.ui.carsMap.marker

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.sixttask.R
import com.example.sixttask.data.json.Car
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.custom_marker_item.view.*


class CustomMarkerInfoWindow(private val activity: Activity, private val car: Car) :
    GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker?): View {
        val itemView = activity.layoutInflater.inflate(R.layout.custom_marker_item, null)
        val modelNameTextView: TextView = itemView.model_name_text_view
        val licensePlateTextView: TextView = itemView.license_plate_text_view
        val fuelTypeTextView: TextView = itemView.fuel_type_text_view
        val fuelLevelTextView: TextView = itemView.fuel_level_text_view
        val transmissionTypeImageView: ImageView = itemView.transmission_type_image_view

        modelNameTextView.text = "${car.make} ${car.series}"
        licensePlateTextView.text = car.licensePlate
        fuelTypeTextView.text = car.fuelType
        fuelLevelTextView.text = "${car.fuelLevel}"

        when {
            car.transmission == "A" -> transmissionTypeImageView.setImageResource(R.drawable.ic_automatic_transmission)
            car.transmission == "M" -> transmissionTypeImageView.setImageResource(R.drawable.ic_manuel_transmission)
        }
        return itemView
    }

    override fun getInfoWindow(marker: Marker?): View? = null
}