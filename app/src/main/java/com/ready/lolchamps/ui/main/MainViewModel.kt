package com.ready.lolchamps.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ready.lolchamps.model.Champion

class MainViewModel: ViewModel() {

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
    private val _champions = MutableLiveData<List<Champion>>()
    val champions: LiveData<List<Champion>> = _champions
}