package com.rogoz208.notesv2.data.repos

import android.content.Context
import androidx.room.Room
import com.rogoz208.notesv2.data.room.NoteDao
import com.rogoz208.notesv2.data.room.NoteRoomDb
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import java.util.*

private const val DB_PATH = "notes.db"

class RoomNotesRepoImpl(context: Context) : NotesRepo {
    private val noteDao: NoteDao = Room.databaseBuilder(
        context,
        NoteRoomDb::class.java,
        DB_PATH
    ).build().noteDao()

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
