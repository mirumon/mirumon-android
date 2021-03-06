package com.redbox.mirumon.main.presentation.common.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.domain.info.DeviceRepository

class OverViewModel(private val repository: DeviceRepository) : ViewModel() {

    val state = MutableLiveData<OverViewState>()

    init {
        state.value = OverViewState.Loading
    }

    fun getOS() {
        repository.getOS({
            state.postValue(OverViewState.Success(it.os[0]))
        }, {
            it.printStackTrace()
            state.postValue(OverViewState.Error)
        })
    }
}