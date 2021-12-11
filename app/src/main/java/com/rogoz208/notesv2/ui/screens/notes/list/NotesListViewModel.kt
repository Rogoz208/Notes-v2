package com.rogoz208.notesv2.ui.screens.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListViewModel(
    private val notesRepo: NotesRepo,
    private val analytics: MyAnalytics
) : ViewModel(), NotesListContract.ViewModel {

    override val notesListLiveData = MutableLiveData<List<NoteEntity>>()
    override val editingNoteLiveData = MutableLiveData<NoteEntity>()
    override val editingNotePositionLiveData = MutableLiveData<Int>()

    init {
        Thread {
            notesListLiveData.postValue(notesRepo.notes)
        }.start()
    }

    override fun onEditNote(note: NoteEntity, position: Int) {
        Thread{
            editingNotePositionLiveData.postValue(position)
            editingNoteLiveData.postValue(note)
        }.start()
    }

    override fun onDeleteNote(note: NoteEntity) {
        Thread{
            notesRepo.deleteNote(note.uid.toString())
            analytics.logEvent("Note \"${note.title}\" is deleted")
            notesListLiveData.postValue(notesRepo.notes)
        }.start()
    }

    override fun onNotesUpdated() {
        Thread{
            notesListLiveData.postValue(notesRepo.notes)
        }.start()
    }
}
