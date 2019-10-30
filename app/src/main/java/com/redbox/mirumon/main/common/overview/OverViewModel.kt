package com.redbox.mirumon.main.common.overview

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.common.CommonRepository
import com.redbox.mirumon.main.network.WebSocketModule
import com.redbox.mirumon.main.network.pojo.ApiMessage
import com.redbox.mirumon.main.network.pojo.DetailsRequest
import com.redbox.mirumon.main.network.pojo.DeviceInfo
import com.redbox.mirumon.main.network.pojo.OperatingSystem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class OverViewModel : ViewModel(), LifecycleObserver {
    private val osInfo = MutableLiveData<OperatingSystem>()
    var webSocket = WebSocketModule.miruWebSocket

    fun notifyWebsocket() {
        val request = ApiMessage(
            "details",
            DetailsRequest(CommonRepository.getAddress())
        )
        webSocket.send(Gson().toJson(request))
    }

    fun observeOsInfo(lifecycleOwner: LifecycleOwner, callback: (OperatingSystem) -> Unit) {
        osInfo.observe(lifecycleOwner, Observer(callback))
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun getOsInfo(response: String) {
        val type = object : TypeToken<ApiMessage<DeviceInfo>>() {}.type
        osInfo.postValue(Gson().fromJson<ApiMessage<DeviceInfo>>(response, type).payload.os[0])
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onFragmentStart() {
        EventBus.getDefault().register(this)
    }
}