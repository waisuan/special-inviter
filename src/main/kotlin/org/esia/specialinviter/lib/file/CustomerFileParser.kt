package org.esia.specialinviter.lib.file

import java.io.File
import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.Customer
import org.json.JSONObject

/*
* Expects a file containing lines of JSON blobs. E.g. of format:-
* {"latitude": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}
* {"latitude": "51.92893", "user_id": 1, "name": "Alice Cahill", "longitude": "-10.27699"}
* {"latitude": "51.8856167", "user_id": 2, "name": "Ian McArdle", "longitude": "-10.4240951"}
* {"latitude": "52.3191841", "user_id": 3, "name": "Jack Enright", "longitude": "-8.5072391"}
*/
object CustomerFileParser : FileParser<Customer>() {
    override fun parse(fileName: String): List<Customer> {
        return File(fileName).readLines().map { line ->
            val blob = JSONObject(line)
            Customer(
                userId = blob.getInt("user_id"),
                name = blob.getString("name"),
                coordinate = Coordinate(latitude = blob.getDouble("latitude"), longitude = blob.getDouble("longitude"))
            )
        }
    }
}
