package com.ready.lolchamps.ui.detail

import androidx.lifecycle.*
import com.ready.lolchamps.model.ChampionInfo
import com.ready.lolchamps.repository.DetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel @AssistedInject constructor(
    detailRepository: DetailRepository,
    @Assisted private val championId: String
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?> = _error

    val championInfo: LiveData<ChampionInfo> = detailRepository.getChampionInfo(
        championId = championId,
        onStart = { _isLoading.value = true },
        onCompletion = { _isLoading.value = false },
        onError = { _error.value = it }
    ).asLiveData()

    @AssistedFactory
    interface Factory {
        fun create(championId: String): DetailViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            championId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(championId) as T
            }
        }
    }
}