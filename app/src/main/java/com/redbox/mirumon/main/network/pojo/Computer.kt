package com.redbox.mirumon.main.network.pojo

import com.google.gson.annotations.SerializedName

data class Computer(
    @SerializedName("mac_adress")
    val macAddress: String,
    val name: String,
    @SerializedName("username")
    val user: String,
    val domain: String,
    val workgroup: String
)
