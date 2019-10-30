package com.redbox.mirumon.main.network.pojo

import com.google.gson.annotations.SerializedName

data class DeviceInfo(
    @SerializedName("mac_address")
    val macAddress: String,
    val name: String,
    @SerializedName("current_user")
    val user: User,
    val domain: String,
    val workgroup: String,
    val os: List<OperatingSystem>
)
