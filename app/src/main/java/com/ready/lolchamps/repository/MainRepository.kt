package com.ready.lolchamps.repository

import com.ready.lolchamps.db.ChampionDao
import com.ready.lolchamps.exceptions.EmptyBodyException
import com.ready.lolchamps.exceptions.NetworkFailureException
import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.network.ChampionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val championService: ChampionService,
    private val championDao: ChampionDao
) {

    fun getAllChampions(
        onStart: () -> Unit,
        onError: (Throwable?) -> Unit
    ) = flow {
        val championsInDB = championDao.getAllChampionList()
        if (championsInDB.isNotEmpty()) {
            emit(championsInDB)
            return@flow
        }

        val response = championService.fetchAllChampions()
        if (response.isSuccessful) {
            val championsFromRemote: List<Champion> =
                response.body()?.toList() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            championDao.insertChampionList(championsFromRemote)
            emit(championsFromRemote)
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }
        .flowOn(Dispatchers.IO)
        .onStart { onStart() }
        .catch { onError(it) }
}