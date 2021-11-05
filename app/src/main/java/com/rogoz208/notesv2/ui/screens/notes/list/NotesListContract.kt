package com.rogoz208.notesv2.ui.screens.notes.list

import com.rogoz208.notesv2.domain.entities.NoteEntity

class NotesListContract {
    interface View {
        fun initRecyclerView(notes: List<NoteEntity>)
        fun updateRecyclerView(notes: List<NoteEntity>)
        fun openAddNoteScreen()
        fun openEditNoteScreen(note: NoteEntity)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onAddNote()
        fun onEditNote(note: NoteEntity)
        fun onDeleteNote(note: NoteEntity)
        fun onNotesUpdated()
    }
}