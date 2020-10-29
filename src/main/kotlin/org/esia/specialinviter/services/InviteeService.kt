package org.esia.specialinviter.services

import org.esia.specialinviter.lib.calculator.GreatCircleDistanceCalculator
import org.esia.specialinviter.lib.file.CustomerFileParser
import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.Customer
import org.esia.specialinviter.models.Distance

/*
* A service class with the purpose of finding out which invitees we should ultimately invite to our gathering!
*/
object InviteeService {
    /*
    * This function goes through each line in the given (customers) file and keeps the ones that are within the given
    * range. It then returns this filtered list in ascending order of the customer's userID.
    */
    fun generateInvitees(sourceCoordinate: Coordinate, maxDistance: Distance, customersFile: String): List<Customer> {
        return CustomerFileParser.parse(customersFile)
            .filter { customer ->
                GreatCircleDistanceCalculator.distanceBetween(sourceCoordinate, customer.coordinate, maxDistance.unit) <= maxDistance.value
            }
            .sortedBy { customer -> customer.userId }
    }
}
