package com.redbox.mirumon.main.domain.common

object CommonRepository {

    var addressIsSet: Boolean = false

    private var address = String()

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String) {
        CommonRepository.address = address
        addressIsSet = true
    }

    fun clearAddress() {
        addressIsSet = false
    }
}