package com.redbox.mirumon.main.presentation.common

object CommonRepository {
    private var address = String()

    fun getAddress(): String {
        return address
    }

    fun setAdress(address: String) {
        this.address = address
    }
}