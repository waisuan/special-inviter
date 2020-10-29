package org.esia.specialinviter.internal

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.DistanceUnit

class CommandLineParser(parser: ArgParser) {
    val maxDistance by parser.storing("-D", "--max-distance", help = "A numerical value denoting the max allowed distance a customer needs to be in to be invited") {
        toDouble()
    }.default(100.0)
    val maxDistanceUnit by parser.mapping(
        "--kilometres" to DistanceUnit.KM,
        "--miles" to DistanceUnit.MI,
        "--nautical-miles" to DistanceUnit.NM,
        help = "The unit of the given max distance"
    ).default(DistanceUnit.KM)
    val sourceCoordinates by parser.storing("-C", "--coordinates", help = "The coordinates of the source/home office in the form of <latitude,longitude> (e.g. 53.339428,-6.257664)") {
        split(",").let { Coordinate(it.first().toDouble(), it.last().toDouble()) }
    }.default(Coordinate(53.339428, -6.257664))
    val customersFile by parser.positional("CUSTOMERS_FILE", help = "Path to file containing a list of customers to potentially invite")
        .default("src/main/resources/customers.txt")
}
