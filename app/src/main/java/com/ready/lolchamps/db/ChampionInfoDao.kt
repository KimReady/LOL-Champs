package com.ready.lolchamps.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ready.lolchamps.model.ChampionInfo


@Dao
interface ChampionInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChampionInfo(championInfo: ChampionInfo)

    @Query("SELECT * FROM ChampionInfo WHERE id = :id")
    suspend fun getChampionInfo(id: String): ChampionInfo?
}