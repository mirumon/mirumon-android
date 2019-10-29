package com.redbox.mirumon.main.device

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.redbox.mirumon.R
import com.redbox.mirumon.main.common.CommonInfoActivity
import kotlinx.android.synthetic.main.activity_device.*

class DeviceActivity : AppCompatActivity() {

    private lateinit var viewModel: DeviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeviceViewModel::class.java)
        setContentView(R.layout.activity_device)

        if (intent.hasExtra("address")) {
            viewModel.getComputer(intent.extras.getString("address"))
            savedInstanceState?.putString("address", intent.extras.getString("address"))
        }

        viewModel.observeDevice(this) {
            Log.d("Activity", "Observing")
            device_name_tv.text = it.name
            device_domain_tv.text = it.domain
            device_group_tv.text = it.workgroup
            device_user_tv.text = it.user.name
            Log.d("debug", it.toString())
        }


        device_back_btn.setOnClickListener {
            super.onBackPressed()
        }

        device_common_btn.setOnClickListener {
            startActivity(Intent(this.applicationContext, CommonInfoActivity::class.java).apply {
                this.putExtra("address", viewModel.device.value?.macAddress)
            })
        }
    }
}

