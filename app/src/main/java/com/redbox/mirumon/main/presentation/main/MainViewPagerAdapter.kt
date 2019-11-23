package com.redbox.mirumon.main.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.redbox.mirumon.main.presentation.main.devicelist.DeviceListFragment
import com.redbox.mirumon.main.presentation.main.grouplist.GroupListFragment

class MainViewPagerAdapter(fm: FragmentManager, val tabCount: Int = 2) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GroupListFragment()
            else -> DeviceListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "My Groups"
            else -> "Devices"
        }
    }

    override fun getCount() = tabCount
}
