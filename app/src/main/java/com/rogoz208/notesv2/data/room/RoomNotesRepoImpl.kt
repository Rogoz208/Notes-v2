package com.rogoz208.notesv2.data.room

import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import java.util.*

class RoomNotesRepoImpl(private val noteDao: NoteDao) : NotesRepo {

    override fun getNotes(callback: (List<NoteEntity>) -> Unit) {
        Thread {
            callback(noteDao.getNotes())
        }.start()
    }

    override fun createNote(note: NoteEntity) {
        Thread {
            val newId = UUID.randomUUID().toString()
            noteDao.add(note.copy(uid = newId))
        }.start()
    }

    override fun deleteNote(uid: String) {
        getNotes { notes ->
            notes.forEach { note ->
                when (note.uid) {
                    uid -> noteDao.delete(note)
                }
            }
        }
    }

    override fun updateNote(uid: String, note: NoteEntity, position: Int) {
        Thread {
            noteDao.update(note)
        }.start()
    }
}
