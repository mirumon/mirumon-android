package com.redbox.mirumon.main.presentation.mainscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.redbox.mirumon.BuildConfig
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    init {
        AppCenter.start(
            application, BuildConfig.APPCENTER_SECRET,
            Analytics::class.java, Crashes::class.java
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        main_fragments_vp.adapter =
            MainViewPagerAdapter(supportFragmentManager)
        main_fragments_vp.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                main_tabs_tl
            )
        )

        main_fragments_vp.currentItem = 1
        main_tabs_tl.getTabAt(1)?.select()

        main_tabs_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                main_fragments_vp.currentItem = p0!!.position
            }
        })
    }
}
