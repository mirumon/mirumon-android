package com.redbox.mirumon.main.presentation.device

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.redbox.mirumon.R
import com.redbox.mirumon.main.extensions.applyErrorState
import com.redbox.mirumon.main.extensions.applyTextLoadingState
import com.redbox.mirumon.main.extensions.applyTextSuccessState
import com.redbox.mirumon.main.presentation.common.CommonInfoActivity
import kotlinx.android.synthetic.main.activity_device.*
import org.koin.android.viewmodel.ext.android.viewModel

class DeviceActivity : AppCompatActivity() {

    private val vm: DeviceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        device_back_btn.setOnClickListener {
            super.onBackPressed()
        }

        vm.state.observe(this, Observer {
            when (it) {
                is DeviceState.InitialState -> vm.getDeviceInfo()
                is DeviceState.LoadingState -> applyTextLoadingState(
                    device_name_tv,
                    device_domain_tv,
                    device_workgroup_tv,
                    device_user_tv
                )

                is DeviceState.SuccessState -> {
                    applyTextSuccessState(
                        device_name_tv,
                        device_domain_tv,
                        device_workgroup_tv,
                        device_user_tv
                    )

                    device_name_tv.text = it.device.name
                    device_domain_tv.text = it.device.domain
                    device_workgroup_tv.text = it.device.workgroup
                    device_user_tv.text = it.device.user.name
                }

                is DeviceState.ErrorState -> {
                    applyErrorState()
                }
            }
        })

        device_common_btn.setOnClickListener {
            startActivity(Intent(this.applicationContext, CommonInfoActivity::class.java))

        }
    }
}
