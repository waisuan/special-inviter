package org.esia.specialinviter.lib.calculator

import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.DistanceUnit

abstract class DistanceCalculator {
    abstract fun distanceBetween(from: Coordinate, to: Coordinate, unit: DistanceUnit = DistanceUnit.KM): Double
}
