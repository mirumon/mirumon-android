package com.redbox.mirumon.main.network

import com.google.gson.annotations.SerializedName

data class Computer(
    val name: String,
    @SerializedName("username") val user: String,
    val domain: String
)
