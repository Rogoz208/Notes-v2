package com.rogoz208.notesv2.data.room.reminders

import androidx.room.*
import com.rogoz208.notesv2.domain.entities.REMINDER_TABLE
import com.rogoz208.notesv2.domain.entities.ReminderEntity

@Dao
interface ReminderDao {
    @Insert
    fun add(reminderEntity: ReminderEntity)

    @Query("SELECT * FROM $REMINDER_TABLE")
    fun getReminders(): List<ReminderEntity>

    @Update
    fun update(reminderEntity: ReminderEntity)

    @Delete
    fun delete(reminderEntity: ReminderEntity)
}