package com.ready.lolchamps.repository

import com.ready.lolchamps.db.ChampionInfoDao
import com.ready.lolchamps.exceptions.EmptyBodyException
import com.ready.lolchamps.exceptions.NetworkFailureException
import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.network.ChampionInfoService
import com.ready.lolchamps.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val championInfoService: ChampionInfoService,
    private val championInfoDao: ChampionInfoDao
) {

    fun getChampionInfo(championId: String) = flow<UiState> {
        championInfoDao.getChampionInfo(championId)?.let {
            emit(UiState.Success(it))
            return@flow
        }

        val response = championInfoService.fetchChampionInfo(championId)
        if (response.isSuccessful) {
            val championInfo: ChampionInfo =
                response.body()?.toList()?.get(0) ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            championInfoDao.insertChampionInfo(championInfo)
            emit(UiState.Success(championInfo))
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }
        .catch { UiState.Error(it) }
        .flowOn(Dispatchers.IO)
}