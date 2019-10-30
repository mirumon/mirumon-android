package com.redbox.mirumon.main.device

import androidx.lifecycle.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.network.WebSocketModule
import com.redbox.mirumon.main.network.pojo.ApiMessage
import com.redbox.mirumon.main.network.pojo.DetailsRequest
import com.redbox.mirumon.main.network.pojo.DeviceInfo
import okhttp3.WebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DeviceViewModel : ViewModel(), LifecycleObserver {
    private var webSocket: WebSocket = WebSocketModule.miruWebSocket
    var device = MutableLiveData<DeviceInfo>()

    fun notifyWebsocket(address: String?) {
        val request = ApiMessage("details", DetailsRequest(address))
        webSocket.send(Gson().toJson(request))
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onRecieve(response: String) {
        val type = object : TypeToken<ApiMessage<DeviceInfo>>() {}.type
        device.postValue(Gson().fromJson<ApiMessage<DeviceInfo>>(response, type).payload)
    }

    fun observeDevice(lifecycleOwner: LifecycleOwner, callback: (DeviceInfo) -> Unit) =
        device.observe(
            lifecycleOwner,
            Observer(callback)
        )

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStart() {
        EventBus.getDefault().register(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onActivityPause() {
        EventBus.getDefault().unregister(this)
    }
}