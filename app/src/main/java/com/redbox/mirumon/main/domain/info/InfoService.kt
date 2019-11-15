package com.redbox.mirumon.main.domain.info

import com.redbox.mirumon.main.domain.pojo.DeviceInfo
import com.redbox.mirumon.main.domain.pojo.Software
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface InfoService {
    @GET("/computers/{mac-adress}/details")
    fun getDetails(@Path("mac-adress") id : String) : Single<DeviceInfo>

    @GET("/computers/{mac-adress}/software")
    fun getSoftware(@Path("mac-adress") id : String) : Single<List<Software>>

    @GET("/computers/{mac-adress}/details")
    fun getOS(@Path("mac-adress") id : String) : Single<DeviceInfo>
}