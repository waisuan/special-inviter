package org.esia.specialinviter.calculator

import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.DistanceUnit
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

/*
* This calculator calculates the distance between two coordinates (latitude + longitude)
* using the great-circle distance formula.
* For more info, refer to https://en.wikipedia.org/wiki/Great-circle_distance
*/
object DistanceCalculator {
    fun distanceBetween(from: Coordinate, to: Coordinate, unit: DistanceUnit = DistanceUnit.KM): Double {
        val distance = Math.toDegrees(acos(
            sin(from.latitudeInRadians()) * sin(to.latitudeInRadians())
                + cos(from.latitudeInRadians()) * cos(to.latitudeInRadians())
                * cos(from.longitudeInRadians() - to.longitudeInRadians())
        ))

        return when(unit) {
            DistanceUnit.KM -> distanceInKilometres(distance)
            DistanceUnit.MI -> distanceInMiles(distance)
            DistanceUnit.NM -> distanceInNauticalMiles(distance)
        }
    }

    private fun distanceInKilometres(distance: Double): Double {
        return distance * 60 * 1.1515 * 1.609344
    }

    private fun distanceInMiles(distance: Double): Double {
        return distance * 60 * 1.1515
    }

    private fun distanceInNauticalMiles(distance: Double): Double {
        return distance * 60 * 1.1515 * 0.8684
    }
}