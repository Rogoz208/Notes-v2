package com.rogoz208.notesv2.domain.entities

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class NoteEntity(
    val uid: String?,
    val title: String,
    val detail: String,
    val creationDate: Long?
) : Parcelable
