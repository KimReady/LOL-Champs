package com.ready.lolchamps.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity
@JsonClass(generateAdapter = true)
data class Champion(
    @field:Json(name = "id") @PrimaryKey val id: String,
    @field:Json(name = "name") val name: String
)