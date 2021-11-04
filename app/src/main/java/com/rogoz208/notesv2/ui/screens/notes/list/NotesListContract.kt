package com.rogoz208.notesv2.ui.screens.notes.list

import com.rogoz208.notesv2.domain.entities.NoteEntity

class NotesListContract {
    interface View {
        fun openAddNoteScreen()
        fun openEditNoteScreen(note: NoteEntity)
        fun deleteNote(note: NoteEntity)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onAddNote()
        fun onEditNote(note: NoteEntity)
        fun onDeleteNote(note: NoteEntity)
    }
}