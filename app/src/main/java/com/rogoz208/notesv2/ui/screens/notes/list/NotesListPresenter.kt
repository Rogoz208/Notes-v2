package com.rogoz208.notesv2.ui.screens.notes.list

import com.rogoz208.notesv2.domain.entities.NoteEntity

class NotesListPresenter: NotesListContract.Presenter {
    private var view: NotesListContract.View? = null

    override fun attach(view: NotesListContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun onAddNote() {
        view?.openAddNoteScreen()
    }

    override fun onEditNote(note: NoteEntity) {
        view?.openEditNoteScreen(note)
    }

    override fun onDeleteNote(note: NoteEntity) {
        TODO("Not yet implemented")
    }
}