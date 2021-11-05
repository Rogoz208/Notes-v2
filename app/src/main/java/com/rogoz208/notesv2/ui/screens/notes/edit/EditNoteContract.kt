package com.rogoz208.notesv2.ui.screens.notes.edit

import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class EditNoteContract {
    interface View{
        fun closeEditNoteScreen()
    }

    interface Presenter{
        fun attach(view: View)
        fun detach()

        fun onNoteSaved(note: NoteEntity?, title: String?, detail: String?, repo: NotesRepo)
    }
}