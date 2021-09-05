package com.ready.lolchamps.ui.detail

import androidx.lifecycle.*
import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    detailRepository: DetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val championId: String = savedStateHandle.get(DetailActivity.CHAMPION_ID_KEY)
        ?: throw IllegalStateException("There is no value of the champion id.")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?> = _error

    val championInfo: StateFlow<ChampionInfo> = detailRepository.getChampionInfo(
        championId = championId,
        onStart = { _isLoading.value = true },
        onCompletion = { _isLoading.value = false },
        onError = { _error.value = it }
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ChampionInfo()
    )
}