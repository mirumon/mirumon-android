package com.redbox.mirumon.main.presentation.main.devicelist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.redbox.mirumon.R
import com.redbox.mirumon.main.domain.common.CommonRepository
import com.redbox.mirumon.main.domain.pojo.DeviceListItem
import com.redbox.mirumon.main.presentation.device.DeviceActivity
import kotlinx.android.synthetic.main.device_list_item.view.common_arch_tv
import kotlinx.android.synthetic.main.device_list_item.view.common_os_tv
import kotlinx.android.synthetic.main.device_list_item.view.device_foreground_cl
import kotlinx.android.synthetic.main.device_list_item.view.device_indicator_iv
import kotlinx.android.synthetic.main.device_list_item.view.device_name_tv
import kotlinx.android.synthetic.main.device_list_item.view.device_power_btn

class DeviceListAdapter(val listener: (mac: String) -> Unit, var deviceList: List<DeviceListItem>) :
    RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.device_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = deviceList.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.apply {
            nameTv.text = deviceList[position].name
            userTv.text = deviceList[position].user
            domainTv.text = deviceList[position].domain

            val anim = AnimationUtils.loadAnimation(context, R.anim.blink)
            anim.startOffset = (100).toLong()
            indicatorIv.animation = anim

            layout.setOnClickListener {
                CommonRepository.setAddress(deviceList[position].macAddress)
                context.startActivity(Intent(context, DeviceActivity::class.java))
            }

            powerButton.setOnClickListener {
                listener(deviceList[position].macAddress)
            }
        }
    }

    class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val powerButton: ImageButton = view.device_power_btn
        val layout: ConstraintLayout = view.device_foreground_cl
        val context: Context = view.context
        val nameTv: TextView = view.device_name_tv
        val userTv: TextView = view.common_arch_tv
        val domainTv: TextView = view.common_os_tv
        val indicatorIv: ImageView = view.device_indicator_iv
    }
}