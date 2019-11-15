package com.redbox.mirumon.main.presentation.common.software

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.domain.SOFTWARE
import com.redbox.mirumon.main.domain.websocket.WebSocketModule
import com.redbox.mirumon.main.domain.pojo.ApiMessage
import com.redbox.mirumon.main.domain.pojo.DetailsRequest
import com.redbox.mirumon.main.domain.pojo.Software
import com.redbox.mirumon.main.presentation.common.CommonRepository
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SoftwareViewModel : ViewModel(), LifecycleObserver {
    var softwareList = MutableLiveData<List<Software>>()
    var webSocket = WebSocketModule.miruWebSocket

    fun notifyWebsocket() {
        val request = ApiMessage(
            SOFTWARE,
            DetailsRequest(CommonRepository.getAddress())
        )
        webSocket.send(Gson().toJson(request))
    }

    fun observeSoftware(lifecycleOwner: LifecycleOwner, callback: (List<Software>) -> Unit) =
        softwareList.observe(
            lifecycleOwner,
            Observer(callback)
        )

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onResponse(response: String) {
        val type = object : TypeToken<ApiMessage<List<Software>>>() {}.type
        softwareList.postValue(Gson().fromJson<ApiMessage<List<Software>>>(response, type).payload)
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
