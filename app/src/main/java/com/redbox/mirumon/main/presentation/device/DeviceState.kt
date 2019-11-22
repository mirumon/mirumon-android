package com.redbox.mirumon.main.presentation.device

import com.redbox.mirumon.main.domain.pojo.DeviceInfo

sealed class DeviceState {
    object InitialState: DeviceState()
    object LoadingState: DeviceState()
    class SuccessState(val device: DeviceInfo): DeviceState()
    object ErrorState: DeviceState()
}