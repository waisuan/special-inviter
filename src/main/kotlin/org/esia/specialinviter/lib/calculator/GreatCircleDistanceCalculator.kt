package org.esia.specialinviter.lib.calculator

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.DistanceUnit

/*
* Given two coordinates in degrees (latitude + longitude), this calculator class calculates the distance between
* them using the great-circle distance formula.
* For more info, refer to https://en.wikipedia.org/wiki/Great-circle_distance
*/
object GreatCircleDistanceCalculator : DistanceCalculator() {
    override fun distanceBetween(from: Coordinate, to: Coordinate, unit: DistanceUnit): Double {
        val distance = Math.toDegrees(acos(
            sin(from.latitudeInRadians()) * sin(to.latitudeInRadians()) +
                cos(from.latitudeInRadians()) * cos(to.latitudeInRadians()) *
                cos(from.longitudeInRadians() - to.longitudeInRadians())
        ))

        return when (unit) {
            DistanceUnit.KM -> distanceInKilometres(distance)
            DistanceUnit.MI -> distanceInMiles(distance)
            DistanceUnit.NM -> distanceInNauticalMiles(distance)
        }
    }

    private fun distanceInKilometres(distance: Double) = distance * 60 * 1.1515 * 1.609344

    private fun distanceInMiles(distance: Double) = distance * 60 * 1.1515

    private fun distanceInNauticalMiles(distance: Double) = distance * 60 * 1.1515 * 0.8684
}
