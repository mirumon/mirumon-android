package com.redbox.mirumon.main.domain.pojo

import com.google.gson.annotations.SerializedName

data class DeviceListItem(
    @SerializedName("mac_address")
    val macAddress: String,
    val name: String,
    @SerializedName("username")
    val user: String,
    val domain: String,
    val workgroup: String
)
