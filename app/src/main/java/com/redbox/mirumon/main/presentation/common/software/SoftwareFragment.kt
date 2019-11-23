package com.redbox.mirumon.main.presentation.common.software

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.mirumon.R
import com.redbox.mirumon.main.extensions.applyErrorState
import com.redbox.mirumon.main.extensions.applyLoadingState
import com.redbox.mirumon.main.extensions.applySuccessState
import kotlinx.android.synthetic.main.fragment_software.rv_button
import kotlinx.android.synthetic.main.fragment_software.software_list_rv
import kotlinx.android.synthetic.main.fragment_software.software_pv
import org.koin.android.viewmodel.ext.android.viewModel

class SoftwareFragment : Fragment() {

    private val vm: SoftwareViewModel by viewModel()
    private lateinit var adapter: SoftwareListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_software, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        software_list_rv.layoutManager = LinearLayoutManager(this.context)
        adapter = SoftwareListAdapter()

        vm.state.observe(this, Observer {
            when (it) {
                is SoftwareState.Initial -> {
                    vm.getSoftware()
                }
                is SoftwareState.Loading -> {
                    rv_button.setActionListener { software_pv.isVisible = !software_pv.isVisible }
                    applyLoadingState(software_list_rv)
                }
                is SoftwareState.Success -> {
                    applySuccessState(software_pv)
                    adapter.softwareList = it.softList
                    software_list_rv.adapter = adapter
                    adapter.notifyDataSetChanged()
                    software_list_rv.isVisible = rv_button.stateOpened
                    rv_button.setActionListener {
                        software_list_rv.isVisible = !software_list_rv.isVisible
                    }
                }
                is SoftwareState.Error -> {
                    applyErrorState()
                }
            }
        })
    }
}
