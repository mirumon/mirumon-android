package com.redbox.mirumon.main.device

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
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

class DeviceViewModel : ViewModel() {
    private var webSocket: WebSocket
    var device = MutableLiveData<DeviceInfo>()

    init {
        EventBus.getDefault().register(this)
        webSocket = WebSocketModule.miruWebSocket
    }

    fun getComputer(address: String) {
        val request = ApiMessage("details", DetailsRequest(address))
        webSocket.send(Gson().toJson(request))
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onRecieve(response: String) {
        Log.d("AAA", response)
        val type = object : TypeToken<ApiMessage<DeviceInfo>>() {}.type
        device.postValue(Gson().fromJson<ApiMessage<DeviceInfo>>(response, type).payload)
    }

    fun observeDevice(lifecycleOwner: LifecycleOwner, callback: (DeviceInfo) -> Unit) =
        device.observe(
            lifecycleOwner,
            Observer(callback)
        )

    override fun onCleared() {
        EventBus.getDefault().unregister(this)
    }
}