package com.rogoz208.notesv2.domain.entities

import android.os.Parcelable
import androidx.annotation.Nullable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteEntity(
    var uid: String?,
    var title: String,
    var detail: String,
    var creationDate: Long?
) : Parcelable