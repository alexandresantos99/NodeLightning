package com.alexandresantos.nodelightning.nodelist.commons

import com.alexandresantos.nodelightning.features.commons.satsToBtc
import com.alexandresantos.nodelightning.features.commons.timestampToBrFormat
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `given sats is valid when call convert function then should returns result formatted`() {
        val sats =  550000L
        val expectedBtc = 0.00550000

        val result = sats.satsToBtc()

        assertEquals(expectedBtc, result)
    }

    @Test
    fun `given timestamp is valid when calls convert function then should returns date formatted `(){
        val timestamp = 1522941222L
        val expectedFormat = "quinta-feira, 5 de abril de 2018 às 12:13:42"

        val result = timestamp.timestampToBrFormat()

        assertEquals(expectedFormat, result)
    }
}