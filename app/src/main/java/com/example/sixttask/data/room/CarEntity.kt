package com.example.sixttask.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sixttask.data.json.Car

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey
    val id: String,
    val modelName: String,
    val modelIdentifier: String,
    val name: String,
    val make: String,
    val group: String,
    val series: String,
    val color: String,
    val fuelType: String,
    val fuelLevel: Double,
    val transmission: String,
    val licensePlate: String,
    val latitude: Double,
    val longitude: Double,
    val innerCleanliness: String,
    val carImageUrl: String
) {
    companion object {
        fun from(car: Car): CarEntity {
            return CarEntity(
                car.id,
                car.modelName,
                car.modelIdentifier,
                car.name,
                car.make,
                car.group,
                car.series,
                car.color,
                car.fuelType,
                car.fuelLevel,
                car.transmission,
                car.licensePlate,
                car.latitude,
                car.longitude,
                car.innerCleanliness,
                car.carImageUrl
            )
        }
    }
}



