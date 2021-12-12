package com.rogoz208.notesv2.data.repos

import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import java.util.*
import kotlin.collections.ArrayList

class MemoryCacheNotesRepoImpl : NotesRepo {
    private val cache: MutableList<NoteEntity> = mutableListOf()

    override fun getNotes(callback: (List<NoteEntity>) -> Unit) {
        callback(ArrayList<NoteEntity>(cache))
    }

    override fun createNote(note: NoteEntity) {
        val newId = UUID.randomUUID().toString()
        val creationDate = Calendar.getInstance().timeInMillis

        cache.add(note.copy(uid = newId, creationDate = creationDate))
    }

    override fun deleteNote(uid: String) {
        for (i in cache.indices) {
            if (cache[i].uid == uid) {
                cache.removeAt(i)
            }
        }
    }

    override fun updateNote(uid: String, note: NoteEntity, position: Int) {
        deleteNote(uid)
        cache.add(position, note.copy(uid = uid))
    }
}
