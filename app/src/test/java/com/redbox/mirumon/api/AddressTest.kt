package com.redbox.mirumon.api

import com.redbox.mirumon.main.domain.common.CommonRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AddressTest {

    private lateinit var address: String

    @Before
    fun setAddress() {
        address = "00-00-00-01-01-01"
        CommonRepository.setAddress(address)
    }

    @Test
    fun parseTest() {
        assertTrue(address == CommonRepository.getAddress())
    }

}