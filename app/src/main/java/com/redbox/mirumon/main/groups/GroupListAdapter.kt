package com.redbox.mirumon.main.groups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redbox.mirumon.R

class GroupListAdapter : RecyclerView.Adapter<GroupListAdapter.GroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.group_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
    }

    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view)
}