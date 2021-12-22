package com.rogoz208.notesv2.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val REMINDER_TABLE = "REMINDER_TABLE"
const val REMINDER_COLUMN_UID = "REMINDER_COLUMN_UID"
const val REMINDER_COLUMN_TITLE = "REMINDER_COLUMN_TITLE"
const val REMINDER_COLUMN_DETAIL = "REMINDER_COLUMN_DETAIL"
const val REMINDER_COLUMN_REMIND_TIME = "REMINDER_COLUMN_REMIND_TIME"

@Entity(tableName = REMINDER_TABLE)
@kotlinx.parcelize.Parcelize
data class ReminderEntity(

    @PrimaryKey
    @ColumnInfo(name = REMINDER_COLUMN_UID)
    val uid: String,

    @ColumnInfo(name = REMINDER_COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = REMINDER_COLUMN_DETAIL)
    val detail: String,

    @ColumnInfo(name = REMINDER_COLUMN_REMIND_TIME)
    val remindTime: Long
) : Parcelable