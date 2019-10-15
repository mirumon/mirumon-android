package com.redbox.mirumon.main.devices

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.redbox.mirumon.R
import com.redbox.mirumon.main.network.pojo.Computer
import kotlinx.android.synthetic.main.device_list_item.view.*
import kotlin.random.Random

class DeviceListAdapter : RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {

    lateinit var deviceList: List<Computer>

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
            anim.startOffset = Random.nextInt(from = 100, until = 500).toLong()
            indicatorIv.animation = anim
        }
    }

    class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val powerButton: ImageButton = view.device_power_btn
        private val foreground: ConstraintLayout = view.device_foreground_cl
        var context: Context
        val nameTv = view.device_name_tv
        val userTv = view.device_user_tv
        val domainTv = view.device_domain_tv
        val indicatorIv = view.device_indicator_iv

        init {

            context = nameTv.context
            powerButton.setOnClickListener {
                Log.d("Dev", "Power Button Clicked")
            }
            foreground.setOnClickListener {
                Log.d("Dev", "Opened Device Info")
            }
        }
    }
}