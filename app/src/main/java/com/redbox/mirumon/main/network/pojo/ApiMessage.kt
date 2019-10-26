package com.redbox.mirumon.main.network.pojo

import com.google.gson.annotations.SerializedName

data class ApiMessage<T>(
    @SerializedName("event_type")
    val eventType: String,
    val payload: T
)