package com.redbox.mirumon.api

import com.redbox.mirumon.main.presentation.common.CommonRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AddressTest {

    lateinit var address : String

    @Before
    fun setAddress() {
        address = "00-00-00-01-01-01"
        CommonRepository.setAdress(address)
    }

    @Test
    fun parseTest() {
        assertTrue(address == CommonRepository.getAddress())
    }

}
