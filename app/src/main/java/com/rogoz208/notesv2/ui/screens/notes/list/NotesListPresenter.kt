package com.rogoz208.notesv2.ui.screens.notes.list

import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListPresenter(private var app: App): NotesListContract.Presenter {
    private var view: NotesListContract.View? = null
    private var repo: NotesRepo = app.getNotesRepo()


    override fun attach(view: NotesListContract.View) {
        this.view = view
        this.view?.showNotes(repo.notes)
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

    override fun onNotesUpdated() {
        view?.showNotes(repo.notes)
    }
}