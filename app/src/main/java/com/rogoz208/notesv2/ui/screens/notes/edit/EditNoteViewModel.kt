package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class EditNoteViewModel(private val repo: NotesRepo) : ViewModel(), EditNoteContract.ViewModel {
    private var note: NoteEntity? = null

    override val noteSavedLiveData = MutableLiveData(false)

    override fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?) {
        if (note == null && (title != "" || detail != "")) {
            this.note = NoteEntity(null, title, detail, null)
            repo.createNote(this.note!!)
        } else {
            note?.let {
                if (title != "" || detail != "") {
                    it.title = title
                    it.detail = detail
                    repo.updateNote(it.uid.toString(), it, position!!)
                } else {
                    repo.deleteNote(it.uid.toString())
                }
            }
        }
        noteSavedLiveData.postValue(true)
    }
}
