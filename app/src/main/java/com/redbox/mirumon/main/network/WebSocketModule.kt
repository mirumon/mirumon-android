package com.redbox.mirumon.main.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

object WebSocketModule {
    private val request = Request.Builder().url("wss://api.mirumon.dev/api/ws").build()
    private val client = OkHttpClient()
    var miruWebSocket: WebSocket

    init {
        miruWebSocket = client.newWebSocket(request, MiruWebSocketListener())
        client.dispatcher().executorService().shutdown()
    }
}