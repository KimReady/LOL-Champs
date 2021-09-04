package com.ready.lolchamps.repository

import com.ready.lolchamps.db.ChampionDao
import com.ready.lolchamps.exceptions.EmptyBodyException
import com.ready.lolchamps.exceptions.NetworkFailureException
import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.network.ChampionService
import com.ready.lolchamps.ui.main.MainUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val championService: ChampionService,
    private val championDao: ChampionDao
) {

    fun getAllChampions() = flow<MainUiState> {
        val championsInDB = championDao.getAllChampionList()
        if (championsInDB.isNotEmpty()) {
            emit(MainUiState.Success(championsInDB))
            return@flow
        }

        val response = championService.fetchAllChampions()
        if (response.isSuccessful) {
            val champions: List<Champion> =
                response.body()?.toList() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            championDao.insertChampionList(champions)
            emit(MainUiState.Success(champions))
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }
        .catch { emit(MainUiState.Error(it)) }
        .flowOn(Dispatchers.IO)
}