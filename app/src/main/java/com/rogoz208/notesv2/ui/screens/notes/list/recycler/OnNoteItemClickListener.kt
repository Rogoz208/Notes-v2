package com.rogoz208.notesv2.ui.screens.notes.list.recycler

import android.view.View
import com.rogoz208.notesv2.domain.entities.NoteEntity

interface OnNoteItemClickListener {

    fun onItemClick(item: NoteEntity, position: Int)

    fun onItemLongClick(item: NoteEntity, itemView: View, position: Int)
}
