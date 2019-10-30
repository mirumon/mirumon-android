package com.redbox.mirumon.main.common.software

import androidx.lifecycle.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.redbox.mirumon.main.common.CommonRepository
import com.redbox.mirumon.main.network.WebSocketModule
import com.redbox.mirumon.main.network.pojo.ApiMessage
import com.redbox.mirumon.main.network.pojo.DetailsRequest
import com.redbox.mirumon.main.network.pojo.Software
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SoftwareViewModel : ViewModel(), LifecycleObserver {
    var softwareList = MutableLiveData<List<Software>>()
    var webSocket = WebSocketModule.miruWebSocket

    fun notifyWebsocket() {
        val request = ApiMessage(
            "installed-programs",
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
