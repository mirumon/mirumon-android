package com.redbox.mirumon.main.domain.pojo

import com.google.gson.annotations.SerializedName

class OperatingSystem(
    val caption: String,
    val version: String,
    @SerializedName("os_architecture")
    val architecture: String,
    @SerializedName("serial_number")
    val serial: String
)