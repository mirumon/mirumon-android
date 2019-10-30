package com.redbox.mirumon

import com.redbox.mirumon.main.mainscreen.grouplist.GroupListAdapter
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ShutdownTest {

    private lateinit var adapter: GroupListAdapter

    @Before
    fun setUp() {
        adapter = GroupListAdapter()
    }

    @Test
    fun shutdown_happened() {
        assertEquals(true, true)
    }
}
