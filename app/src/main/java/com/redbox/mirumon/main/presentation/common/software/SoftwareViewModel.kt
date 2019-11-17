package com.redbox.mirumon.main.presentation.common.software

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbox.mirumon.main.domain.info.InfoRepository

class SoftwareViewModel(private val repository: InfoRepository) : ViewModel() {

    val state = MutableLiveData<SoftwareState>()

    init {
        state.value = SoftwareState.Initial
    }

    fun getSoftware(loading: Boolean) {
        state.postValue(SoftwareState.Loading(loading))
        repository.getSoftware({
            state.postValue(SoftwareState.Success(it))
        }, {
            state.postValue(SoftwareState.Error)
        })
    }
}
