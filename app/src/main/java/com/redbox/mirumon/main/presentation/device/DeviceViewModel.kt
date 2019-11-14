package com.redbox.mirumon.main.presentation.device

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.domain.DEVICE_INFO
import com.redbox.mirumon.main.domain.WebSocketModule
import com.redbox.mirumon.main.domain.pojo.ApiMessage
import com.redbox.mirumon.main.domain.pojo.DetailsRequest
import com.redbox.mirumon.main.domain.pojo.DeviceInfo
import okhttp3.WebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DeviceViewModel : ViewModel(), LifecycleObserver {
    private var webSocket: WebSocket = WebSocketModule.miruWebSocket
    var device = MutableLiveData<DeviceInfo>()

    fun notifyWebsocket(address: String?) {
        val request = ApiMessage(DEVICE_INFO, DetailsRequest(address))
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

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onActivityPause() {
        EventBus.getDefault().unregister(this)
    }
}