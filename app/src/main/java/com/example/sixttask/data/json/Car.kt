package com.example.sixttask.data.json

import com.example.sixttask.data.room.CarEntity
import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("id") val id: String,
    @SerializedName("modelName") val modelName: String,
    @SerializedName("modelIdentifier") val modelIdentifier: String,
    @SerializedName("name") val name: String,
    @SerializedName("make") val make: String,
    @SerializedName("group") val group: String,
    @SerializedName("series") val series: String,
    @SerializedName("color") val color: String,
    @SerializedName("fuelType") val fuelType: String,
    @SerializedName("fuelLevel") val fuelLevel: Double,
    @SerializedName("transmission") val transmission: String,
    @SerializedName("licensePlate") val licensePlate: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("innerCleanliness") val innerCleanliness: String,
    @SerializedName("carImageUrl") val carImageUrl: String
) {
    companion object {
        fun from(carEntity: CarEntity): Car {
            return Car(
                carEntity.id,
                carEntity.modelName,
                carEntity.modelIdentifier,
                carEntity.name,
                carEntity.make,
                carEntity.group,
                carEntity.series,
                carEntity.color,
                carEntity.fuelType,
                carEntity.fuelLevel,
                carEntity.transmission,
                carEntity.licensePlate,
                carEntity.latitude,
                carEntity.longitude,
                carEntity.innerCleanliness,
                carEntity.carImageUrl
            )
        }
    }
}

