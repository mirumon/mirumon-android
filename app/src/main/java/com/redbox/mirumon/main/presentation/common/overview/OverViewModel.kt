package com.redbox.mirumon.main.presentation.common.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.domain.info.InfoRepository

class OverViewModel(private val repository: InfoRepository) : ViewModel() {

    val state = MutableLiveData<OverViewState>()

    init {
        state.value = OverViewState.Initial
    }

    fun getOS() {
        repository.getOS({
            state.postValue(OverViewState.Success(it.os[0]))
        }, {
            it.printStackTrace()
            state.postValue(OverViewState.Error)
        })
    }
}