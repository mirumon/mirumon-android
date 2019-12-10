package com.redbox.mirumon.main.domain.websocket.dispatcher

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.domain.pojo.ApiMessage
import com.redbox.mirumon.main.domain.pojo.DeviceListItem
import com.redbox.mirumon.main.domain.websocket.DEVICE_LIST
import com.redbox.mirumon.main.domain.websocket.WebSocketModule
import com.redbox.mirumon.main.domain.websocket.events.DeviceListEvent
import okhttp3.WebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONObject

object WebSocketDispatcher {

    private val webSocket: WebSocket = WebSocketModule.miruWebSocket

    init {
        EventBus.getDefault().register(this)
    }

    fun <T> sendEvent(event: String, payload: T) {
        val request = ApiMessage(event, payload)
        webSocket.send(Gson().toJson(request))
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onRecieve(response: String) {
        val r = JSONObject(response)
        when (r.get("event_type")) {
            DEVICE_LIST -> {
                val type = object : TypeToken<ApiMessage<ArrayList<DeviceListItem>>>() {}.type
                EventBus.getDefault().post(
                    DeviceListEvent(
                        Gson().fromJson<ApiMessage<ArrayList<DeviceListItem>>>(
                            response, type
                        ).payload
                    )
                )
            }
        }
    }
}