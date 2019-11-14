package com.redbox.mirumon.main.presentation.mainscreen.devicelist

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
    private lateinit var adapter : DeviceListAdapter


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

        list_refresh.setColorSchemeResources(
            R.color.colorPrimaryDark
        )

        list_refresh.setOnRefreshListener {
            listViewModel.getDevices()
            list_refresh.isRefreshing = false
        }

        listViewModel.observeDevices(this) {
            adapter = DeviceListAdapter(listViewModel::shutDown)
            adapter.deviceList = it
            device_list_rv.adapter = adapter.apply { notifyDataSetChanged() }
        }

        device_list_rv.layoutManager = LinearLayoutManager(this.context)
    }

    fun shutDown(macAdress : String){
        listViewModel.shutDown(macAdress)
    }
}