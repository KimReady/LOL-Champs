package com.ready.lolchamps.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ready.lolchamps.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity
@JsonClass(generateAdapter = true)
data class ChampionInfo (
    @field:Json(name = "id") @PrimaryKey val id: String = "",
    @field:Json(name = "name") val name: String? = null,
    @field:Json(name = "title") val title: String? = null,
    @field:Json(name = "lore") val lore: String? = null,
    @field:Json(name = "image") val image: Image? = null,
    @field:Json(name = "tags") val tags: List<String>? = null,
    @field:Json(name = "skins") val skins: List<Skin>? = null,
    @field:Json(name = "spells") val spells: List<Spell>? = null,
    @field:Json(name = "passive") val passive: Passive? = null,
) {

    @JsonClass(generateAdapter = true)
    data class Skin(
        @field:Json(name = "num") val num: Int,
        @field:Json(name = "name") val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Spell(
        @field:Json(name = "id") val id: String,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "description") val description: String,
        @field:Json(name = "image") val image: Image
    )

    @JsonClass(generateAdapter = true)
    data class Passive(
        @field:Json(name = "name") val name: String,
        @field:Json(name = "description") val description: String,
        @field:Json(name = "image") val image: Image
    )
}
