package com.ready.lolchamps.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @field:Json(name = "full") val fileName: String
)