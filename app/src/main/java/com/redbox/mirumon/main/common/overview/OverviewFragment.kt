package com.redbox.mirumon.main.common.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment() {

    private var viewModel = OverViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(OverViewModel::class.java)
        lifecycle.addObserver(viewModel)

        viewModel.notifyWebsocket()

        viewModel.observeOsInfo(this) {
            common_os_tv.text = it.caption
            common_version_tv.text = it.version
            common_arch_tv.text = it.architecture
            common_serial_tv.text = it.serial
        }
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }
}
