package com.ready.lolchamps.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ready.lolchamps.model.Champion
import com.ready.lolchamps.repository.MainRepository
import com.ready.lolchamps.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    val uiState: StateFlow<UiState<List<Champion>>> = mainRepository.getAllChampions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = UiState.Loading
        )
}