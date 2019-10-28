package com.example.sixttask.ui.carsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sixttask.R
import com.example.sixttask.data.json.Car
import com.example.sixttask.util.extensions.loadUrl
import kotlinx.android.synthetic.main.car_list_item.view.*

class CarsAdapter(
    private var cars: List<Car>,
    val onItemClickListener: (car: Car) -> Unit
) : RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.car_list_item, parent, false)
        return CarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.carImageView.loadUrl(car.carImageUrl, 0)
        holder.modelNameTextView.text = "${car.make} ${car.series}"
        holder.licensePlateTextView.text = car.licensePlate
        holder.fuelTypeTextView.text = car.fuelType
        holder.fuelLevelTextView.text = "${car.fuelLevel}"

        when {
            car.transmission == "A" -> holder.transmissionTypeImageView.setImageResource(R.drawable.ic_automatic_transmission)
            car.transmission == "M" -> holder.transmissionTypeImageView.setImageResource(R.drawable.ic_manuel_transmission)
        }
    }

    override fun getItemCount(): Int = cars.size

    fun setCars(cars: List<Car>) {
        this.cars = cars
    }

    class CarViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImageView: ImageView = itemView.car_image_view
        val modelNameTextView: TextView = itemView.model_name_text_view
        val licensePlateTextView: TextView = itemView.license_plate_text_view
        val fuelTypeTextView: TextView = itemView.fuel_type_text_view
        val fuelLevelTextView: TextView = itemView.fuel_level_text_view
        val transmissionTypeImageView: ImageView = itemView.transmission_type_image_view
    }

}