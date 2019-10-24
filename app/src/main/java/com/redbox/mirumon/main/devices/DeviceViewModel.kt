package com.redbox.mirumon.main.devices

import android.media.session.MediaSession
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.network.WebSocketModule
import com.redbox.mirumon.main.network.pojo.Computer
import com.redbox.mirumon.main.network.pojo.Request
import okhttp3.WebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DeviceViewModel : ViewModel() {

    private val deviceList = MutableLiveData<List<Computer>>()
    private val webSocket: WebSocket

    init {
        EventBus.getDefault().register(this)
        webSocket = WebSocketModule.getWebSocket()
    }

    fun getDevices() {
        Log.d("getDevices", "?")
        val request = Request("computers-list", deviceList.value)
        webSocket.send(Gson().toJson(request))
        Log.d("Json", Gson().toJson(request))
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onRecieve(response: String) {
       Log.d("RESPONSE", response)
       val type = object : TypeToken<Request<List<Computer>>>(){}.type
        deviceList.postValue(Gson().fromJson<Request<List<Computer>>>(response, type).payload)
    }

    fun observeDevices(lifecycleOwner: LifecycleOwner, callbackList: (List<Computer>) -> Unit) =
        deviceList.observe(
            lifecycleOwner,
            Observer(callbackList)
        )

    override fun onCleared() {
        EventBus.getDefault().unregister(this)
    }
}