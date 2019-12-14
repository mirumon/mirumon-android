package com.redbox.mirumon.main.domain.info

import com.redbox.mirumon.main.domain.pojo.Command
import com.redbox.mirumon.main.domain.pojo.DeviceInfo
import com.redbox.mirumon.main.domain.pojo.Software
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DeviceService {
    @GET("/computers/{mac-address}/details")
    fun getDetails(@Path("mac-address") id: String): Single<DeviceInfo>

    @GET("/computers/{mac-address}/installed-programs")
    fun getSoftware(@Path("mac-address") id: String): Single<ArrayList<Software>>

    @GET("/computers/{mac-address}/details")
    fun getOS(@Path("mac-address") id: String): Single<DeviceInfo>

    @POST("/computers/{mac-address}/shutdown")
    fun shutdownPC(@Path("mac-address") id: String): Single<String>

    @POST("/computers/{mac-address}/execute")
    fun executeCommand(@Path("mac-address") id: String, @Body command: Command): Single<String>
}