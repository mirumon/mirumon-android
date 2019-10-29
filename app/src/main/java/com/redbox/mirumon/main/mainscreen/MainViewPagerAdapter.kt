package com.redbox.mirumon.main.mainscreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.redbox.mirumon.main.mainscreen.devicelist.DeviceListFragment
import com.redbox.mirumon.main.mainscreen.grouplist.GroupListFragment

class MainViewPagerAdapter(fm: FragmentManager, val tabCount: Int = 2) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> DeviceListFragment()
            else -> GroupListFragment()
        }
    }

    override fun getCount() = tabCount
}
