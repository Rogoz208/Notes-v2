package com.rogoz208.notesv2.ui.screens.reminders.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.ItemReminderViewHolderBinding
import com.rogoz208.notesv2.domain.entities.ReminderEntity
import java.text.SimpleDateFormat
import java.util.*

class ReminderViewHolder(
    parent: ViewGroup,
    private val clickListener: OnReminderItemClickListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_reminder_view_holder, parent, false)
) {
    private val binding by viewBinding(ItemReminderViewHolderBinding::bind)

    fun bind(reminder: ReminderEntity) {
        binding.titleTextView.text = reminder.title
        binding.detailTextView.text = reminder.detail
        val date = Date(reminder.remindTime)
        val dateFormat = SimpleDateFormat("dd.MM.y HH:mm", Locale.getDefault())
        binding.remindTimeTextView.text = dateFormat.format(date)
        itemView.setOnClickListener {
            clickListener.onItemClick(reminder, this.layoutPosition)
        }
        itemView.setOnLongClickListener {
            clickListener.onItemLongClick(reminder, itemView, this.layoutPosition)
            true
        }
    }
}