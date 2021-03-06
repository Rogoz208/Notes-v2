package com.rogoz208.notesv2.domain.entities

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class NoteEntity(
    var uid: String?,
    var title: String,
    var detail: String,
    var creationDate: Long?
) : Parcelable

// TODO: var to val (lesson-8 - 00:45:17)