package com.redbox.mirumon.main.presentation.mainscreen.devicelist

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.domain.DEVICE_LIST
import com.redbox.mirumon.main.domain.SHUTDOWN
import com.redbox.mirumon.main.domain.WebSocketModule
import com.redbox.mirumon.main.domain.pojo.ApiMessage
import com.redbox.mirumon.main.domain.pojo.DetailsRequest
import com.redbox.mirumon.main.domain.pojo.DeviceListItem
import okhttp3.WebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DeviceListViewModel : ViewModel(), LifecycleObserver {

    private val deviceList = MutableLiveData<List<DeviceListItem>>()
    private val webSocket: WebSocket = WebSocketModule.miruWebSocket

    fun getDevices() {
        val request = ApiMessage(DEVICE_LIST, null)
        webSocket.send(Gson().toJson(request))
    }

    fun shutDown(macAdress: String) {
        val request = ApiMessage(SHUTDOWN, DetailsRequest(macAdress))
        Log.e("SHUT", request.toString())
        webSocket.send(Gson().toJson(request))
    }


    fun observeDevices(
        lifecycleOwner: LifecycleOwner,
        callbackList: (List<DeviceListItem>) -> Unit
    ) =
        deviceList.observe(
            lifecycleOwner,
            Observer(callbackList)
        )

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onRecieve(response: String) {
        Log.d("T", response)
        val type = object : TypeToken<ApiMessage<List<DeviceListItem>>>() {}.type
        deviceList.postValue(
            Gson().fromJson<ApiMessage<List<DeviceListItem>>>(
                response,
                type
            ).payload
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onLifeCycleStart() {
        EventBus.getDefault().register(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onLifeCyclePause() {
        EventBus.getDefault().unregister(this)
    }
}