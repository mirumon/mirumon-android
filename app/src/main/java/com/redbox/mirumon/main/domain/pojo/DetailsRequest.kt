package com.redbox.mirumon.main.domain.pojo

import com.google.gson.annotations.SerializedName

data class DetailsRequest(
    @SerializedName("computer_id")
    val computerId: String?

)