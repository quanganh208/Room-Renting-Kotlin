package com.itptit.roomrenting.data.remote.dto.invoice

import com.itptit.roomrenting.domain.model.invoice.Data

data class AllInvoiceResponse(
    val `data`: List<Data>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)