package com.redbox.mirumon.main.presentation.main.devicelist

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.domain.pojo.DetailsRequest
import com.redbox.mirumon.main.domain.pojo.DeviceListItem
import com.redbox.mirumon.main.domain.websocket.DEVICE_LIST
import com.redbox.mirumon.main.domain.websocket.SHUTDOWN
import com.redbox.mirumon.main.domain.websocket.dispatcher.WebSocketDispatcher
import com.redbox.mirumon.main.domain.websocket.events.DeviceListEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DeviceListViewModel : ViewModel(), LifecycleObserver {

    private val deviceList = MutableLiveData<ArrayList<DeviceListItem>>()

    fun getDevices() {
        WebSocketDispatcher.sendEvent(DEVICE_LIST, null)
    }

    fun shutDown(macAddress: String) {
        WebSocketDispatcher.sendEvent(SHUTDOWN, DetailsRequest(macAddress))
    }

    fun observeDevices(
        lifecycleOwner: LifecycleOwner,
        callbackList: (ArrayList<DeviceListItem>) -> Unit
    ) =
        deviceList.observe(
            lifecycleOwner,
            Observer(callbackList)
        )

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onRecieve(response: DeviceListEvent) {
        deviceList.postValue(response.list)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onLifeCycleStart() {
        EventBus.getDefault().register(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onLifeCyclePause() {
        EventBus.getDefault().unregister(this)
    }
}