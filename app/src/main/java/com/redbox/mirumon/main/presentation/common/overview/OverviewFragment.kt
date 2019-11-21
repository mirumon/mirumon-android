package com.redbox.mirumon.main.presentation.common.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.redbox.mirumon.R
import com.redbox.mirumon.main.extensions.applyTextLoadingState
import com.redbox.mirumon.main.extensions.applyTextSuccessState
import kotlinx.android.synthetic.main.fragment_overview.*
import org.koin.android.viewmodel.ext.android.viewModel

class OverviewFragment : Fragment() {

    private val vm: OverViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm.state.observe(this, Observer {
            when (it) {
                is OverViewState.Initial -> {
                    vm.getOS()
                    applyTextLoadingState(
                        common_os_tv,
                        common_arch_tv,
                        common_version_tv,
                        common_serial_tv
                    )
                }
                is OverViewState.Success -> {
                    applyTextSuccessState(
                        common_os_tv,
                        common_arch_tv,
                        common_version_tv,
                        common_serial_tv
                    )
                    common_os_tv.text = it.os.caption
                    common_arch_tv.text = it.os.architecture
                    common_serial_tv.text = it.os.serial
                    common_version_tv.text = it.os.version
                }
                is OverViewState.Error -> {
                    Toast
                        .makeText(this.context, getText(R.string.error_message), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })


        return inflater.inflate(R.layout.fragment_overview, container, false)
    }
}
