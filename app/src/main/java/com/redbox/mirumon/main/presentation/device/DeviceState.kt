package com.redbox.mirumon.main.presentation.device

import com.redbox.mirumon.main.domain.pojo.DeviceInfo

sealed class DeviceState {
    object Initial : DeviceState()
    object Loading : DeviceState()
    class Success(val device: DeviceInfo) : DeviceState()
    object Error : DeviceState()
}