package org.esia.specialinviter.services

import java.lang.IllegalArgumentException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.Customer
import org.esia.specialinviter.models.Distance
import org.esia.specialinviter.models.DistanceUnit
import org.junit.Test

class InviteeServiceTests {
    private val customersFile = "src/test/resources/MockCustomersMedium.txt"
    private val sourceCoordinate = Coordinate(latitude = 53.339428, longitude = -6.257664)

    @Test
    fun `expects a list of invitees in ascending user ID order that are within the given range of the source coordinate`() {
        var maxDistance = Distance(value = 100.0, unit = DistanceUnit.KM)
        var invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        assertThat(invitees.size).isEqualTo(7)
        assertThat(invitees).isEqualTo(listOf(
            Customer(userId = 4, name = "Ian Kehoe", coordinate = Coordinate(latitude = 53.2451022, longitude = -6.238335)),
            Customer(userId = 5, name = "Nora Dempsey", coordinate = Coordinate(latitude = 53.1302756, longitude = -6.2397222)),
            Customer(userId = 6, name = "Theresa Enright", coordinate = Coordinate(latitude = 53.1229599, longitude = -6.2705202)),
            Customer(userId = 8, name = "Eoin Ahearn", coordinate = Coordinate(latitude = 54.0894797, longitude = -6.18671)),
            Customer(userId = 11, name = "Richard Finnegan", coordinate = Coordinate(latitude = 53.008769, longitude = -6.1056711)),
            Customer(userId = 12, name = "Christina McArdle", coordinate = Coordinate(latitude = 52.986375, longitude = -6.043701)),
            Customer(userId = 26, name = "Stephen McArdle", coordinate = Coordinate(latitude = 53.038056, longitude = -7.653889))
        ))

        maxDistance = Distance(value = 50.0, unit = DistanceUnit.KM)
        invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        assertThat(invitees.size).isEqualTo(5)
        assertThat(invitees).isEqualTo(listOf(
            Customer(userId = 4, name = "Ian Kehoe", coordinate = Coordinate(latitude = 53.2451022, longitude = -6.238335)),
            Customer(userId = 5, name = "Nora Dempsey", coordinate = Coordinate(latitude = 53.1302756, longitude = -6.2397222)),
            Customer(userId = 6, name = "Theresa Enright", coordinate = Coordinate(latitude = 53.1229599, longitude = -6.2705202)),
            Customer(userId = 11, name = "Richard Finnegan", coordinate = Coordinate(latitude = 53.008769, longitude = -6.1056711)),
            Customer(userId = 12, name = "Christina McArdle", coordinate = Coordinate(latitude = 52.986375, longitude = -6.043701))
        ))

        maxDistance = Distance(value = 30.0, unit = DistanceUnit.KM)
        invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        assertThat(invitees.size).isEqualTo(3)
        assertThat(invitees).isEqualTo(listOf(
            Customer(userId = 4, name = "Ian Kehoe", coordinate = Coordinate(latitude = 53.2451022, longitude = -6.238335)),
            Customer(userId = 5, name = "Nora Dempsey", coordinate = Coordinate(latitude = 53.1302756, longitude = -6.2397222)),
            Customer(userId = 6, name = "Theresa Enright", coordinate = Coordinate(latitude = 53.1229599, longitude = -6.2705202))
        ))
    }

    @Test
    fun `expects a valid list of invitees when given a valid distance unit`() {
        var maxDistance = Distance(value = 100.0, unit = DistanceUnit.KM)
        var invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        assertThat(invitees.size).isEqualTo(7)

        maxDistance = Distance(value = 62.1371, unit = DistanceUnit.MI)
        invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        assertThat(invitees.size).isEqualTo(7)

        maxDistance = Distance(value = 53.9957, unit = DistanceUnit.NM)
        invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        assertThat(invitees.size).isEqualTo(7)
    }

    @Test
    fun `expects an empty list of invitees if customers file is empty`() {
        val maxDistance = Distance(value = 100.0, unit = DistanceUnit.KM)
        val invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, "src/test/resources/MockEmptyCustomers.txt")
        assertThat(invitees).isEmpty()
    }

    @Test
    fun `expects an empty list of invitees if no customer is within the given range of the source coordinate`() {
        val maxDistance = Distance(value = 5.0, unit = DistanceUnit.KM)
        val invitees = InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        assertThat(invitees).isEmpty()
    }

    @Test
    fun `expects an IllegalArgumentException exception type if given distance unit is unknown`() {
        assertThatThrownBy {
            val maxDistance = Distance(value = 5.0, unit = DistanceUnit.valueOf("GalacticMiles"))
            InviteeService.generateInvitees(sourceCoordinate, maxDistance, customersFile)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("No enum constant")
    }
}
