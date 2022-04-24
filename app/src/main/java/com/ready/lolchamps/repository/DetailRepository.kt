package com.ready.lolchamps.repository

import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.ui.base.UiState
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getChampionInfo(championId: String): Flow<UiState<ChampionInfo>>
}