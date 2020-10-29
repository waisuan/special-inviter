package org.esia.specialinviter

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import org.esia.specialinviter.internal.CommandLineParser
import org.esia.specialinviter.models.Distance
import org.esia.specialinviter.services.InviteeService

fun main(args: Array<String>) = mainBody {
    ArgParser(args).parseInto(::CommandLineParser).run {
        InviteeService
            .generateInvitees(sourceCoordinates, Distance(maxDistance, maxDistanceUnit), customersFile)
            .forEach { customer ->
                println("UserID: ${customer.userId}, Name: ${customer.name}")
            }
    }
}
