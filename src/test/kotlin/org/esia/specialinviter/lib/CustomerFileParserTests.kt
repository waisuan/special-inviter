package org.esia.specialinviter.lib

import java.io.FileNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.esia.specialinviter.lib.file.CustomerFileParser
import org.esia.specialinviter.models.Coordinate
import org.esia.specialinviter.models.Customer
import org.json.JSONException
import org.junit.Test

class CustomerFileParserTests {
    @Test
    fun `expects a list of Customer objects when given a file`() {
        val customers = CustomerFileParser.parse("src/test/resources/MockCustomers.txt")
        assertThat(customers.sortedBy { it.userId }).isEqualTo(listOf(
            Customer(1, "Alice Cahill", Coordinate(latitude = 51.92893, longitude = -10.27699)),
            Customer(12, "Christina McArdle", Coordinate(latitude = 52.986375, longitude = -6.043701))
        ))
    }

    @Test
    fun `expects an empty list when given an empty file`() {
        val customers = CustomerFileParser.parse("src/test/resources/MockEmptyCustomers.txt")
        assertThat(customers).isEqualTo(emptyList<Customer>())
    }

    @Test
    fun `expects a FileNotFoundException exception type if given a non-existent file`() {
        assertThatThrownBy { CustomerFileParser.parse("src/test/resources/DoesNotExistFile.txt") }
            .isInstanceOf(FileNotFoundException::class.java)
            .hasMessageContaining("No such file or directory")
    }

    @Test
    fun `expects a JSONException exception type if given a non-JSON formatted file`() {
        assertThatThrownBy { CustomerFileParser.parse("src/test/resources/MockMalformedCustomers.txt") }
            .isInstanceOf(JSONException::class.java)
            .hasMessageContaining("A JSONObject text must begin with '{'")
    }

    @Test
    fun `expects a JSONException exception type if given a file that has missing contents`() {
        assertThatThrownBy { CustomerFileParser.parse("src/test/resources/MockMalformedCustomers2.txt") }
            .isInstanceOf(JSONException::class.java)
            .hasMessageContaining("not found")
    }
}
