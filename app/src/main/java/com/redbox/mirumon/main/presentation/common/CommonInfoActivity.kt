package com.redbox.mirumon.main.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.activity_common.*

class CommonInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_common)

        common_vp.adapter = CommonViewPagerAdapter(supportFragmentManager)
        common_tabs_tl.setupWithViewPager(common_vp)
        common_tabs_tl.getTabAt(0)?.setIcon(R.drawable.ic_info)
        common_tabs_tl.getTabAt(1)?.setIcon(R.drawable.ic_software)

        common_vp.currentItem = 1
        common_tabs_tl.getTabAt(1)!!.select()
        common_screen_tv.text = getText(R.string.common_software)

        common_tabs_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.position) {
                    0 -> common_screen_tv.text = getText(R.string.command_overview)
                    1 -> common_screen_tv.text = getText(R.string.common_software)
                }
            }
        })

        common_back_btn.setOnClickListener {
            super.onBackPressed()
        }
    }
}
