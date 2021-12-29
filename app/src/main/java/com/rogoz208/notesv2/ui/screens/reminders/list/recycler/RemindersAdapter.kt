package com.rogoz208.notesv2.ui.screens.reminders.list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogoz208.notesv2.domain.entities.ReminderEntity

class RemindersAdapter : RecyclerView.Adapter<ReminderViewHolder>() {
    var data: List<ReminderEntity> = ArrayList()
        get() = ArrayList(field)

    private var clickListener: OnReminderItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        return ReminderViewHolder(parent, clickListener!!)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): ReminderEntity {
        return data[position]
    }

    fun setOnItemClickListener(listener: OnReminderItemClickListener) {
        clickListener = listener
    }
}