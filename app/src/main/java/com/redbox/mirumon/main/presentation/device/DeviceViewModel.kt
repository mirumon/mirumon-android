package com.redbox.mirumon.main.presentation.device

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.domain.info.InfoRepository

class DeviceViewModel(private val repository: InfoRepository) : ViewModel() {

    val state = MutableLiveData<DeviceState>()

    init {
        state.postValue(DeviceState.Initial)
    }

    fun getDeviceInfo() {
        state.postValue(DeviceState.Loading)
        repository.getDeviceInfo({
            state.postValue(DeviceState.Success(it))
        }, {
            it.printStackTrace()
        })
    }
}