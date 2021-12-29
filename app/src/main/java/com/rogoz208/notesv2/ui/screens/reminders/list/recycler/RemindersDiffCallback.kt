package com.rogoz208.notesv2.ui.screens.reminders.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.rogoz208.notesv2.domain.entities.ReminderEntity

class RemindersDiffCallback(
    private val oldList: List<ReminderEntity>,
    private val newList: List<ReminderEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uid == newList[newItemPosition].uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].detail == newList[newItemPosition].detail
                && oldList[oldItemPosition].remindTime == newList[newItemPosition].remindTime
    }
}