package com.redbox.mirumon.main.presentation.common.overview

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
import com.redbox.mirumon.main.domain.pojo.OperatingSystem
import com.redbox.mirumon.main.presentation.common.CommonRepository
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class OverViewModel : ViewModel(), LifecycleObserver {
    private val osInfo = MutableLiveData<OperatingSystem>()
    private var webSocket = WebSocketModule.miruWebSocket

    fun notifyWebsocket() {
        val request = ApiMessage(DEVICE_INFO, DetailsRequest(CommonRepository.getAddress()))
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

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onFragmentPause() {
        EventBus.getDefault().unregister(this)
    }
}