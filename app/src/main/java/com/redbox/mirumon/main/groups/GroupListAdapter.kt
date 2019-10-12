package com.redbox.mirumon.main.groups

import android.util.Log
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

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
    }

    fun shutDown(position: Int){
        Log.d("Shutting down", "PC #$position")
    }

    class GroupViewHolder(view : View) : RecyclerView.ViewHolder(view)
}