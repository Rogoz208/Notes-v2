package com.rogoz208.notesv2.ui.screens.notes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogoz208.notesv2.domain.entities.NoteEntity

class NotesAdapter : RecyclerView.Adapter<NoteViewHolder>() {
    private var data: List<NoteEntity> = ArrayList()
    private var clickListener: OnItemClickListener? = null

    fun setData(data: List<NoteEntity>){
        this.data = data
    }

    fun getData(): List<NoteEntity> {
        return ArrayList(data)
    }

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