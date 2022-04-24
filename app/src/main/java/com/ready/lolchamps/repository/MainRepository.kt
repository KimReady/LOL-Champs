package com.ready.lolchamps.repository

import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.ui.base.UiState
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAllChampions(): Flow<UiState<List<Champion>>>
}