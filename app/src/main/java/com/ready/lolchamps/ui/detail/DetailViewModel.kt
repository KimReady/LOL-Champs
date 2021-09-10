package com.ready.lolchamps.ui.detail

import androidx.lifecycle.*
import com.ready.lolchamps.model.ChampionInfo

class DetailViewModel(
    championId: String
) : ViewModel() {

    /**
     * TODO - If loading data, true. otherwise, false.
     */
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * TODO - If an error occurs while fetching data, set the message.
     */
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    /**
     * TODO - If you succeeded in getting the data from local or remote, set it up.
     */
    private val _championInfo = MutableLiveData<ChampionInfo>()
    val championInfo: LiveData<ChampionInfo> = _championInfo
}