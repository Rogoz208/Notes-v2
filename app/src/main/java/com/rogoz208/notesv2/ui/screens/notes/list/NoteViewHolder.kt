package com.rogoz208.notesv2.ui.screens.notes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.ItemNoteViewHolderBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity

class NoteViewHolder(parent: ViewGroup, private val clickListener: OnItemClickListener) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note_view_holder, parent, false)
    ) {
    private val binding by viewBinding(ItemNoteViewHolderBinding::bind)

    fun bind(note: NoteEntity) {
        binding.titleTextView.text = note.title
        binding.detailTextView.text = note.detail
        itemView.setOnClickListener {
            clickListener.onItemClick(note, this.layoutPosition)
        }
        itemView.setOnLongClickListener {
            clickListener.onItemLongClick(note, itemView, this.layoutPosition)
            true
        }
    }
}