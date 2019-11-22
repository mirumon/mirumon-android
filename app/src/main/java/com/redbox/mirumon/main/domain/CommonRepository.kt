package com.redbox.mirumon.main.domain

object CommonRepository {
    private var address = String()

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }
}