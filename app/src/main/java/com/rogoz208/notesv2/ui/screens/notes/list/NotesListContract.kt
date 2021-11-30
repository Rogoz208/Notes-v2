package com.rogoz208.notesv2.ui.screens.notes.list

import androidx.lifecycle.LiveData
import com.rogoz208.notesv2.domain.entities.NoteEntity

class NotesListContract {

    interface ViewModel {
        val notesListLiveData: LiveData<List<NoteEntity>>
        val editingNoteLiveData: LiveData<NoteEntity>
        val editingNotePositionLiveData: LiveData<Int>

        fun onEditNote(note: NoteEntity, position: Int)
        fun onDeleteNote(note: NoteEntity)
        fun onNotesUpdated()
    }
}
