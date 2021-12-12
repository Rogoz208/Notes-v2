package com.rogoz208.notesv2.domain.repos

import com.rogoz208.notesv2.domain.entities.NoteEntity

interface NotesRepo {

    fun getNotes(callback: (List<NoteEntity>) -> Unit)

    fun createNote(note: NoteEntity)

    fun deleteNote(uid: String)

    fun updateNote(uid: String, note: NoteEntity, position: Int)

}
