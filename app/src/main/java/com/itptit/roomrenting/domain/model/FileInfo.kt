package com.itptit.roomrenting.domain.model

import android.net.Uri

data class FileInfo(
    val fileName: String,
    val fileSize: Double,
    val uri: Uri
)
