package com.redbox.mirumon.main.presentation.common.overview

import com.redbox.mirumon.main.domain.pojo.OperatingSystem

sealed class OverViewState {
    object Loading : OverViewState()
    class Success(val os: OperatingSystem) : OverViewState()
    object Error : OverViewState()
}