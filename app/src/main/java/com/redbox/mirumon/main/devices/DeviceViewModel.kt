package com.redbox.mirumon.main.devices

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.network.ApiService
import com.redbox.mirumon.main.network.NetworkModule
import com.redbox.mirumon.main.network.pojo.Computer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DeviceViewModel : ViewModel() {

    val deviceList = MutableLiveData<List<Computer>>()

    fun getDevices() {
        NetworkModule.retrofit.create(ApiService::class.java)
            .getComputers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Computer>>() {
                override fun onSuccess(computerList: List<Computer>) {
                    deviceList.postValue(computerList)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun observeDevices(lifecycleOwner: LifecycleOwner, callbackList: (List<Computer>) -> Unit) =
        deviceList.observe(
            lifecycleOwner,
            Observer(callbackList)
        )
}