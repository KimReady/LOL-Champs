package com.ready.lolchamps.ui.main

import com.ready.lolchamps.model.Champion

sealed class MainUiState {
    object Loading: MainUiState()
    data class Success(val data: List<Champion>): MainUiState()
    data class Error(val error: Throwable?): MainUiState()
}