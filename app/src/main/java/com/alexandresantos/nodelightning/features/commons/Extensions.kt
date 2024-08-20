package com.alexandresantos.nodelightning.features.commons

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.satsToBtc(): Double {
    val satsPerBtc = 100000000
    return this.toDouble() / satsPerBtc
}

fun Long.timestampToBrFormat(): String {
    val instant = Instant.ofEpochSecond(this)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter =
        DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy 'às' HH:mm:ss", Locale("pt", "BR"))
    return localDateTime.format(formatter)
}