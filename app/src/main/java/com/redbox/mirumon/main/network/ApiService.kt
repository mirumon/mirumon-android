package com.redbox.mirumon.main.network

import com.redbox.mirumon.main.network.pojo.Computer
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/computers")
    fun getComputers(): Single<List<Computer>>
}