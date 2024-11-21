package com.itptit.roomrenting.data.remote.dto.asset

import com.itptit.roomrenting.domain.model.asset.Data

data class AllAssetResponse(
    val data: List<Data>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)