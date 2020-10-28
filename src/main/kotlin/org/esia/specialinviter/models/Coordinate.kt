package org.esia.specialinviter.models

data class Coordinate(val latitude: Double, val longitude: Double) {
    fun latitudeInRadians(): Double = Math.toRadians(latitude)
    fun longitudeInRadians(): Double = Math.toRadians(longitude)
}
