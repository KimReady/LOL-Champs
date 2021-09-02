package com.ready.lolchamps.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ChampionResponse<T>(
    @field:Json(name = "data") val data: Map<String, T>
) {
    fun toList(): List<T> = data.values.toList()
}