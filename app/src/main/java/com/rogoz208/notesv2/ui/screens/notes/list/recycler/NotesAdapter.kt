package com.rogoz208.notesv2.ui.screens.notes.list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogoz208.notesv2.domain.entities.NoteEntity

class NotesAdapter : RecyclerView.Adapter<NoteViewHolder>() {
    var data: List<NoteEntity> = ArrayList()
        get() = ArrayList(field)

    private var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent, clickListener!!)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): NoteEntity {
        return data[position]
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }
}
