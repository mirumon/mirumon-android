package com.redbox.mirumon.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_fragments_vp.adapter =
            MainViewPagerAdapter(supportFragmentManager)
        main_fragments_vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main_tabs_tl))

        main_tabs_tl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                main_fragments_vp.currentItem = p0!!.position
            }

        })

        main_search_ib.setOnClickListener{
            Log.d("CLicked", "search")
        }

    }
}

