package com.rogoz208.notesv2.data.room.reminders

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rogoz208.notesv2.domain.entities.ReminderEntity

@Database(
    entities = [ReminderEntity::class],
    version = 1
)

abstract class ReminderRoomDb : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}