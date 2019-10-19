package com.redbox.mirumon.main.devices

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.network.pojo.Computer

class DeviceViewModel : ViewModel() {

    val deviceList = MutableLiveData<List<Computer>>()

    fun observeDevices(lifecycleOwner: LifecycleOwner, callbackList: (List<Computer>) -> Unit) =
        deviceList.observe(
            lifecycleOwner,
            Observer(callbackList)
        )
}