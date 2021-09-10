package com.ready.lolchamps.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Champion(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String
)