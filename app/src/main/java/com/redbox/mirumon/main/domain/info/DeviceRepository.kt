package com.redbox.mirumon.main.domain.info

import com.redbox.mirumon.main.domain.common.CommonRepository
import com.redbox.mirumon.main.domain.pojo.Command
import com.redbox.mirumon.main.domain.pojo.DeviceInfo
import com.redbox.mirumon.main.domain.pojo.Software
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DeviceRepository(private val service: DeviceService) {

    fun getSoftware(
        onSuccess: (ArrayList<Software>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return service
            .getSoftware(CommonRepository.getAddress())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
    }

    fun getOS(
        onSuccess: (DeviceInfo) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return service
            .getOS(CommonRepository.getAddress())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
    }

    fun getDeviceInfo(
        onSuccess: (DeviceInfo) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return service
            .getDetails(CommonRepository.getAddress())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
    }

    fun shutdownPC(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return service.shutdownPC(CommonRepository.getAddress()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
    }

    fun executeCommand(
        command: Command,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return service.executeCommand(CommonRepository.getAddress(), command).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
    }
}