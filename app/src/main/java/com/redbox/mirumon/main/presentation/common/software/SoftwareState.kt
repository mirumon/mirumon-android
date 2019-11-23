package com.redbox.mirumon.main.presentation.common.software

import com.redbox.mirumon.main.domain.pojo.Software

sealed class SoftwareState {
    object Initial : SoftwareState()
    object Loading : SoftwareState()
    class Success(val softList: ArrayList<Software>) : SoftwareState()
    object Error : SoftwareState()
}