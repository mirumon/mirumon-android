package com.redbox.mirumon.main.domain.info

import com.redbox.mirumon.main.domain.pojo.DeviceInfo
import com.redbox.mirumon.main.domain.pojo.Software
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface InfoService {
    @GET("/computers/{mac-address}/details")
    fun getDetails(@Path("mac-address") id: String): Single<DeviceInfo>

    @GET("/computers/{mac-address}/installed-programs")
    fun getSoftware(@Path("mac-address") id: String): Single<ArrayList<Software>>

    @GET("/computers/{mac-address}/details")
    fun getOS(@Path("mac-address") id: String): Single<DeviceInfo>
}