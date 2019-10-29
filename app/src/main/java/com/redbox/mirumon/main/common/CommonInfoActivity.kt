package com.redbox.mirumon.main.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.activity_common.*

class CommonInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        common_back_btn.setOnClickListener {
            super.onBackPressed()
        }
    }
}
