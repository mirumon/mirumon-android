package com.redbox.mirumon.main.devices

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.network.WebSocketModule
import com.redbox.mirumon.main.network.pojo.Computer
import com.redbox.mirumon.main.network.pojo.Request
import okhttp3.Response
import okhttp3.WebSocket
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DeviceViewModel : ViewModel() {

    val deviceList = MutableLiveData<List<Computer>>()
    val webSocket : WebSocket

    init {
        EventBus.getDefault().register(this)
        webSocket = WebSocketModule.getWebSocket()
    }

    fun getDevices(){
        var request = Request("computer_list", deviceList.value)
        webSocket.send(request.toString())
    }

    @Subscribe (threadMode = ThreadMode.BACKGROUND)
    fun onRecieve(response: String){
        Log.d("response", response)
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