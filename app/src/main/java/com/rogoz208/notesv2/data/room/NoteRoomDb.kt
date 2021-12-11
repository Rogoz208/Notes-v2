package com.rogoz208.notesv2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rogoz208.notesv2.domain.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteRoomDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}