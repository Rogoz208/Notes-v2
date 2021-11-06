package com.rogoz208.notesv2.ui.screens.notes.edit

import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class EditNotePresenter : EditNoteContract.Presenter {
    private var view: EditNoteContract.View? = null
    private var note: NoteEntity? = null

    override fun attach(view: EditNoteContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun onNoteSaved(note: NoteEntity?, title: String, detail: String, repo: NotesRepo) {
        if (note == null) {
            this.note = NoteEntity(null, title, detail, null)
            repo.createNote(this.note!!)
        } else {
            note.title = title
            note.detail = detail
            repo.updateNote(note.uid.toString(), note)
        }
        view?.closeEditNoteScreen()
    }
}