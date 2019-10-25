package com.redbox.mirumon.main.network

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.greenrobot.eventbus.EventBus

class MiruWebSocketListener : WebSocketListener() {

    val OK_CLOSED_STATUS = 1000

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        EventBus.getDefault().post(text)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        t.printStackTrace()
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(OK_CLOSED_STATUS, null)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        EventBus.getDefault().post(bytes)
    }
}