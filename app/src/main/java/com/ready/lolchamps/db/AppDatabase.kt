package com.ready.lolchamps.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.model.ChampionInfo


/**
 * TODO - If you want to use Local DB, use this class. If not, ignore all classes in the `db` package.
 */
@Database(entities = [Champion::class, ChampionInfo::class], version = 1, exportSchema = true)
@TypeConverters(value = [ImageTypeConverter::class, StringListTypeConverter::class, SkinTypeConverter::class, SpellTypeConverter::class, PassiveTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun championDao(): ChampionDao
    abstract fun championInfoDao(): ChampionInfoDao
}