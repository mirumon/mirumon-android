package com.redbox.mirumon.main.mainscreen.devicelist

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.network.WebSocketModule
import com.redbox.mirumon.main.network.pojo.ApiMessage
import com.redbox.mirumon.main.network.pojo.DeviceListItem
import okhttp3.WebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DeviceListViewModel : ViewModel(), LifecycleObserver {

    private val deviceList = MutableLiveData<List<DeviceListItem>>()
    private val webSocket: WebSocket = WebSocketModule.miruWebSocket

    fun getDevices() {
        val request = ApiMessage("computers-list", deviceList.value)
        webSocket.send(Gson().toJson(request))
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onRecieve(response: String) {
        val type = object : TypeToken<ApiMessage<List<DeviceListItem>>>() {}.type
        deviceList.postValue(
            Gson().fromJson<ApiMessage<List<DeviceListItem>>>(
                response,
                type
            ).payload
        )
    }

    fun observeDevices(
        lifecycleOwner: LifecycleOwner,
        callbackList: (List<DeviceListItem>) -> Unit
    ) =
        deviceList.observe(
            lifecycleOwner,
            Observer(callbackList)
        )

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onLifeCycleStart() {
        EventBus.getDefault().register(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onLifeCyclePause() {
        EventBus.getDefault().unregister(this)
    }
}