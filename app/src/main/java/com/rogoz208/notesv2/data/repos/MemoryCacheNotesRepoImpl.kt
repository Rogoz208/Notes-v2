package com.rogoz208.notesv2.data.repos

import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import java.util.*
import kotlin.collections.ArrayList

class MemoryCacheNotesRepoImpl : NotesRepo {
    private val cache: MutableList<NoteEntity> = mutableListOf()

    override val notes: List<NoteEntity>
        get() {
            return ArrayList<NoteEntity>(cache)
        }

    override fun createNote(note: NoteEntity): String {
        val newId = UUID.randomUUID().toString()
        val creationDate = Calendar.getInstance().timeInMillis

        cache.add(note.copy(uid = newId, creationDate = creationDate))
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

    override fun updateNote(uid: String, note: NoteEntity, position: Int): Boolean {
        deleteNote(uid)
        cache.add(position, note.copy(uid = uid))
        return true
    }
}
