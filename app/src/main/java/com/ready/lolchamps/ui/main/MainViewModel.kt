package com.ready.lolchamps.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ready.lolchamps.repository.MainRepository
import com.ready.lolchamps.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getAllChampions() = viewModelScope.launch {
        mainRepository.getAllChampions(
            onStart = { _uiState.value = UiState.Loading },
            onError = { _uiState.value = UiState.Error(it) }
        )
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }
}