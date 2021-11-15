package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.LiveData
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class EditNoteContract {

    interface ViewModel {
        val noteSavedLiveData: LiveData<Boolean>

        fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?)
    }
}
