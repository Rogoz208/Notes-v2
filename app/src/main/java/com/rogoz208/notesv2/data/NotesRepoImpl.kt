package com.rogoz208.notesv2.data

import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import java.util.*
import kotlin.collections.ArrayList

class NotesRepoImpl : NotesRepo {
    private val cache = ArrayList<NoteEntity>()

    override fun getNotes(): List<NoteEntity> {
        return ArrayList(cache)
    }

    override fun createNote(note: NoteEntity): String {
        val newId = UUID.randomUUID().toString()
        val creationDate = Calendar.getInstance().timeInMillis
        note.uid = newId
        note.creationDate = creationDate
        cache.add(note)
        return newId
    }

    override fun deleteNote(uid: String): Boolean {
        for (i in cache.indices) {
            if (cache[i].uid == uid) {
                cache.removeAt(i)
                return true
            }
        }
        return false
    }

    override fun updateNote(uid: String, note: NoteEntity): Boolean {
        deleteNote(uid)
        note.uid = uid
        cache.add(note)
        return true
    }
}