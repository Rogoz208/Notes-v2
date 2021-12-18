package com.rogoz208.notesv2.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val NOTE_TABLE = "notes"
const val NOTE_COLUMN_UID = "uid"
const val NOTE_COLUMN_TITLE = "title"
const val NOTE_COLUMN_DETAIL = "detail"
const val NOTE_COLUMN_CREATION_DATE = "creationDate"
const val NOTE_COLUMN_LATITUDE = "latitude"
const val NOTE_COLUMN_LONGITUDE = "longitude"
const val NOTE_COLUMN_ADDRESS = "address"

@Entity(tableName = NOTE_TABLE)
@kotlinx.parcelize.Parcelize
data class NoteEntity(

    @PrimaryKey
    @ColumnInfo(name = NOTE_COLUMN_UID)
    val uid: String,

    @ColumnInfo(name = NOTE_COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = NOTE_COLUMN_DETAIL)
    val detail: String,

    @ColumnInfo(name = NOTE_COLUMN_CREATION_DATE)
    val creationDate: Long?,

    @ColumnInfo(name = NOTE_COLUMN_LATITUDE)
    val latitude: Double?,

    @ColumnInfo(name = NOTE_COLUMN_LONGITUDE)
    val longitude: Double?,

    @ColumnInfo(name = NOTE_COLUMN_ADDRESS)
    val address: String?

) : Parcelable
