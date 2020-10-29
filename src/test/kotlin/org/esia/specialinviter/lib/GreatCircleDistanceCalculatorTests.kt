package org.esia.specialinviter.lib

import org.assertj.core.api.Assertions.assertThat
import org.esia.specialinviter.lib.calculator.GreatCircleDistanceCalculator
import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.DistanceUnit
import org.junit.Test

class GreatCircleDistanceCalculatorTests {
    @Test
    fun `expects the great-circle distance between two coordinates in Kilometres by default`() {
        val from = Coordinate(latitude = 53.339, longitude = -6.2577)
        val to = Coordinate(latitude = 52.986375, longitude = -6.043701)

        val distance = GreatCircleDistanceCalculator.distanceBetween(from, to)
        assertThat(distance).isEqualTo(41.72283584381012)
    }

    @Test
    fun `expects the great-circle distance between two coordinates in Miles`() {
        val from = Coordinate(latitude = 53.339, longitude = -6.2577)
        val to = Coordinate(latitude = 52.986375, longitude = -6.043701)

        val distance = GreatCircleDistanceCalculator.distanceBetween(from, to, DistanceUnit.MI)
        assertThat(distance).isEqualTo(25.92536825179086)
    }

    @Test
    fun `expects the great-circle distance between two coordinates in Nautical Miles`() {
        val from = Coordinate(latitude = 53.339, longitude = -6.2577)
        val to = Coordinate(latitude = 52.986375, longitude = -6.043701)

        val distance = GreatCircleDistanceCalculator.distanceBetween(from, to, DistanceUnit.NM)
        assertThat(distance).isEqualTo(22.513589789855182)
    }
}
