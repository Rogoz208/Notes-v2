package com.rogoz208.notesv2.ui.screens.notes.list

import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListPresenter(app: App): NotesListContract.Presenter {
    private var view: NotesListContract.View? = null
    private var repo: NotesRepo = app.getNotesRepo()


    override fun attach(view: NotesListContract.View) {
        this.view = view
        this.view?.initRecyclerView(repo.notes)
    }

    override fun detach() {
        this.view = null
    }

    override fun onAddNote() {
        view?.openAddNoteScreen()

    }

    override fun onEditNote(note: NoteEntity, position: Int) {
        view?.openEditNoteScreen(note, position)
    }

    override fun onDeleteNote(note: NoteEntity) {
        repo.deleteNote(note.uid.toString())
        view?.updateRecyclerView(repo.notes)
    }

    override fun onNotesUpdated() {
        view?.updateRecyclerView(repo.notes)
    }
}