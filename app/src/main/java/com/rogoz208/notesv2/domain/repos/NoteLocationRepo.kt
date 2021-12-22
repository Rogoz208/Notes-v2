package com.rogoz208.notesv2.domain.repos

import com.rogoz208.notesv2.domain.entities.NoteLocation

interface NoteLocationRepo {

    fun getCurrentLocation(callback: (NoteLocation) -> Unit)
}