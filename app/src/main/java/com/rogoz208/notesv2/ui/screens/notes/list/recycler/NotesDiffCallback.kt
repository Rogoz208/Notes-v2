package com.rogoz208.notesv2.ui.screens.notes.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.rogoz208.notesv2.domain.entities.NoteEntity

class NotesDiffCallback(
    private val oldList: List<NoteEntity>,
    private val newList: List<NoteEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uid == newList[newItemPosition].uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].detail == newList[newItemPosition].detail
                && oldList[oldItemPosition].creationDate == newList[newItemPosition].creationDate
    }
}
