package com.ready.lolchamps.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ready.lolchamps.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity
@JsonClass(generateAdapter = true)
data class ChampionInfo (
    @field:Json(name = "name") @PrimaryKey val name: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "lore") val lore: String,
    @field:Json(name = "image") val image: Image,
    @field:Json(name = "skins") val skins: List<Skin>,
    @field:Json(name = "spells") val spells: List<Spell>,
    @field:Json(name = "passive") val passive: Passive,
) {

    @JsonClass(generateAdapter = true)
    data class Skin(
        @field:Json(name = "num") val num: String,
        @field:Json(name = "name") val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Spell(
        @field:Json(name = "id") val id: String,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "description") val description: String,
        @field:Json(name = "image") val image: Image
    ) {
        fun getSkillCommand() = id.last().toString()
    }

    @JsonClass(generateAdapter = true)
    data class Passive(
        @field:Json(name = "name") val name: String,
        @field:Json(name = "description") val description: String,
        @field:Json(name = "image") val image: Image
    )
}
