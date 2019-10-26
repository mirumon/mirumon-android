package com.redbox.mirumon.main.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

object WebSocketModule {
    val request = Request.Builder().url("wss://api.mirumon.dev/api/ws").build()
    val client = OkHttpClient()

    fun getWebSocket(): WebSocket {
        val webSocket: WebSocket = client.newWebSocket(request, MiruWebSocketListener())
        client.dispatcher().executorService().shutdown()
        return webSocket
    }
}