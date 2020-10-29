package org.esia.specialinviter

import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.Distance
import org.esia.specialinviter.models.DistanceUnit
import org.esia.specialinviter.services.InviteeService

fun main() {
    val maxDistance = 100.0
    val distanceUnit = DistanceUnit.KM
    val dublinOffice = Coordinate(53.339428, -6.257664)

    InviteeService
        .generateInvitees(dublinOffice, Distance(maxDistance, distanceUnit), "src/main/resources/customers.txt")
        .forEach { customer ->
            println("UserID: ${customer.userId}, Name: ${customer.name}")
        }
}
