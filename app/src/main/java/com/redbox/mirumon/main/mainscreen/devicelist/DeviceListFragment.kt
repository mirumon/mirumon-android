package com.redbox.mirumon.main.mainscreen.devicelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.fragment_device_list.*

class DeviceListFragment : Fragment() {

    private lateinit var listViewModel: DeviceListViewModel
    private val deviceListAdapter = DeviceListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel = ViewModelProviders.of(this).get(DeviceListViewModel::class.java)
        lifecycle.addObserver(listViewModel)
        return inflater.inflate(R.layout.fragment_device_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.getDevices()

        listViewModel.observeDevices(this) {
            deviceListAdapter.deviceList = it
            device_list_rv.adapter = deviceListAdapter.apply { notifyDataSetChanged() }
        }

        device_list_rv.layoutManager = LinearLayoutManager(this.context)
    }

}