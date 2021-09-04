package com.ready.lolchamps.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.model.Image
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject


@ProvidedTypeConverter
class ImageTypeConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): Image? {
        val adapter: JsonAdapter<Image> = moshi.adapter(Image::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromImage(type: Image): String {
        val adapter: JsonAdapter<Image> = moshi.adapter(Image::class.java)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class StringListTypeConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromImage(type: List<String>): String {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class SkinTypeConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<ChampionInfo.Skin>? {
        val listType = Types.newParameterizedType(List::class.java, ChampionInfo.Skin::class.java)
        val adapter: JsonAdapter<List<ChampionInfo.Skin>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromImage(type: List<ChampionInfo.Skin>): String {
        val listType = Types.newParameterizedType(List::class.java, ChampionInfo.Skin::class.java)
        val adapter: JsonAdapter<List<ChampionInfo.Skin>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class SpellTypeConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<ChampionInfo.Spell>? {
        val listType = Types.newParameterizedType(List::class.java, ChampionInfo.Spell::class.java)
        val adapter: JsonAdapter<List<ChampionInfo.Spell>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromImage(type: List<ChampionInfo.Spell>): String {
        val listType = Types.newParameterizedType(List::class.java, ChampionInfo.Spell::class.java)
        val adapter: JsonAdapter<List<ChampionInfo.Spell>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class PassiveTypeConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): ChampionInfo.Passive? {
        val adapter: JsonAdapter<ChampionInfo.Passive> = moshi.adapter(ChampionInfo.Passive::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromImage(type: ChampionInfo.Passive): String {
        val adapter: JsonAdapter<ChampionInfo.Passive> = moshi.adapter(ChampionInfo.Passive::class.java)
        return adapter.toJson(type)
    }
}