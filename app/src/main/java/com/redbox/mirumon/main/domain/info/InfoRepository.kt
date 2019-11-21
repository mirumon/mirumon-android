package com.redbox.mirumon.main.domain.info

import com.redbox.mirumon.main.domain.pojo.DeviceInfo
import com.redbox.mirumon.main.domain.pojo.Software
import com.redbox.mirumon.main.presentation.common.CommonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class InfoRepository(private val service: InfoService) {

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

}