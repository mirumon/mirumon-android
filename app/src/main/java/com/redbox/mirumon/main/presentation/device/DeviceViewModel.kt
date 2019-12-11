package com.redbox.mirumon.main.presentation.device

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.domain.info.DeviceRepository
import com.redbox.mirumon.main.domain.pojo.Command

class DeviceViewModel(private val rep: DeviceRepository) : ViewModel() {

    val state = MutableLiveData<DeviceState>()

    init {
        state.value = DeviceState.Initial
    }

    fun getDeviceInfo() {
        state.postValue(DeviceState.Loading)
        rep.getDeviceInfo({
            state.postValue(DeviceState.Success(it))
        }, {
            it.printStackTrace()
        })
    }

    fun shutdownPC() {
        rep.shutdownPC({ state.postValue(DeviceState.ShuttingDown) }, {})
    }

    fun executeСommand(command: String) {
        rep.executeCommand(Command(command), {}, {})
    }
}