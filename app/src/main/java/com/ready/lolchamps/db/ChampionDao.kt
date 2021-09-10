package com.ready.lolchamps.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ready.lolchamps.model.Champion


@Dao
interface ChampionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChampionList(championList: List<Champion>)

    @Query("SELECT * FROM Champion")
    suspend fun getAllChampionList(): List<Champion>
}