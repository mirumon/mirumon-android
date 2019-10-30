package com.redbox.mirumon.main.device

import android.content.Intent
import android.os.Bundle
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
        lifecycle.addObserver(viewModel)
        setContentView(R.layout.activity_device)

        if (intent.hasExtra("address")) {
            viewModel.notifyWebsocket(intent.extras.getString("address"))
            savedInstanceState?.putString("address", intent.extras.getString("address"))
        }

        viewModel.observeDevice(this) {
            device_name_tv.text = it.name
            device_domain_tv.text = it.domain
            device_workgroup_tv.text = it.workgroup
            device_user_tv.text = it.user.name
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
