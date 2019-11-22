package com.redbox.mirumon.api

import com.redbox.mirumon.main.presentation.main.grouplist.GroupListAdapter

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GroupListTest {

    private lateinit var adapter: GroupListAdapter

    @Before
    fun setAddress() {
        adapter = GroupListAdapter()
    }

    @Test
    fun parseTest() {
        assertTrue(adapter.itemCount == 0)
    }

}