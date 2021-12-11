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

    override val notes: List<NoteEntity>
        get() = noteDao.getNotes()

    override fun createNote(note: NoteEntity): String {
        val newId = UUID.randomUUID().toString()
        noteDao.add(note.copy(uid = newId))
        return newId
    }

    override fun deleteNote(uid: String): Boolean {
        notes.forEach { note ->
            when (note.uid) {
                uid -> {
                    noteDao.delete(note)
                    return true
                }
            }
        }
        return false
    }

    override fun updateNote(uid: String, note: NoteEntity, position: Int): Boolean {
        noteDao.update(note)
        return true
    }
}
