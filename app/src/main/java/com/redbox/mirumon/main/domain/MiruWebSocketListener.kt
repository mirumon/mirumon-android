package com.redbox.mirumon.main.domain

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.greenrobot.eventbus.EventBus

class MiruWebSocketListener : WebSocketListener() {

    private val OK_CLOSED_STATUS = 1000

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
}