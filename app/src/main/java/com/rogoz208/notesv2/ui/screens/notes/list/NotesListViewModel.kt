package com.rogoz208.notesv2.ui.screens.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListViewModel(app: App) : ViewModel(), NotesListContract.ViewModel {
    private var repo: NotesRepo = app.getNotesRepo()

    override val notesListLiveData = MutableLiveData<List<NoteEntity>>()
    override val editingNoteLiveData = MutableLiveData<NoteEntity>()
    override val editingNotePositionLiveData = MutableLiveData<Int>()

    init {
        notesListLiveData.value = repo.notes
    }

    override fun onEditNote(note: NoteEntity, position: Int) {
        editingNotePositionLiveData.postValue(position)
        editingNoteLiveData.postValue(note)
    }

    override fun onDeleteNote(note: NoteEntity) {
        repo.deleteNote(note.uid.toString())
        notesListLiveData.postValue(repo.notes)
    }

    override fun onNotesUpdated() {
        notesListLiveData.postValue(repo.notes)
    }
}
