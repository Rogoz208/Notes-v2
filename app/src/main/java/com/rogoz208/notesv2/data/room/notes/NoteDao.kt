package com.rogoz208.notesv2.data.room.notes

import androidx.room.*
import com.rogoz208.notesv2.domain.entities.NOTE_TABLE
import com.rogoz208.notesv2.domain.entities.NoteEntity

@Dao
interface NoteDao {
    @Insert
    fun add(noteEntity: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getNotes(): List<NoteEntity>

    @Update
    fun update(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)
}
