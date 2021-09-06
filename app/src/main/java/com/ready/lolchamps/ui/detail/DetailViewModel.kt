package com.ready.lolchamps.ui.detail

import androidx.lifecycle.*
import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.repository.DetailRepository
import com.ready.lolchamps.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    detailRepository: DetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val championId: String = savedStateHandle.get(DetailActivity.CHAMPION_ID_KEY)
        ?: throw IllegalStateException("There is no value of the champion id.")

    val uiState: StateFlow<UiState> = detailRepository.getChampionInfo(championId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = UiState.Loading
        )

    val championInfo: StateFlow<ChampionInfo?> = uiState.mapLatest { state ->
        if (state is UiState.Success<*>) {
            state.data as ChampionInfo
        } else {
            ChampionInfo()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ChampionInfo()
    )
}