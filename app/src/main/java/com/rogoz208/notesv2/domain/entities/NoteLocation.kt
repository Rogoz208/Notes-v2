package com.rogoz208.notesv2.domain.entities

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class NoteLocation(
    val latitude: Double,
    val longitude: Double,
    val address: String
) : Parcelable