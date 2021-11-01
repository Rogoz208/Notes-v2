package com.rogoz208.notesv2.domain.repos

import com.rogoz208.notesv2.domain.entities.NoteEntity

interface NotesRepo {

    val notes: List<NoteEntity>

    fun createNote(note: NoteEntity): String

    fun deleteNote(uid: String): Boolean

    fun updateNote(uid: String, note: NoteEntity): Boolean

}