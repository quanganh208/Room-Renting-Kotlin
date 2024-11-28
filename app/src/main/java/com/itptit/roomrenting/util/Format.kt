package com.itptit.roomrenting.util

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter

class Format {
    fun time(time: String): String {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        val dateTime =
            OffsetDateTime.parse(time, formatter).withOffsetSameInstant(ZoneOffset.ofHours(7))
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yyyy")
        return dateTime.format(outputFormatter)
    }
}