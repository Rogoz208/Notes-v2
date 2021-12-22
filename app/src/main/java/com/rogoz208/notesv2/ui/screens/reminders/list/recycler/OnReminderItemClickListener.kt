package com.rogoz208.notesv2.ui.screens.reminders.list.recycler

import android.view.View
import com.rogoz208.notesv2.domain.entities.ReminderEntity

interface OnReminderItemClickListener {

    fun onItemClick(item: ReminderEntity, position: Int)

    fun onItemLongClick(item: ReminderEntity, itemView: View, position: Int)
}