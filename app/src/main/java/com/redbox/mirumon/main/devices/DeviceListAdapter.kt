package com.redbox.mirumon.main.devices

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.RecyclerView
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.device_list_item.view.*

class DeviceListAdapter : RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.device_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
    }

    class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val powerButton: ImageButton = view.device_power_btn
        private val foreground: ConstraintLayout = view.device_foreground_cl

        init {
            powerButton.setOnClickListener {
                Log.d("Dev", "Power Button Clicked")
            }
            foreground.setOnClickListener {
                Log.d("Dev", "Opened Device Info")
            }
        }
    }
}