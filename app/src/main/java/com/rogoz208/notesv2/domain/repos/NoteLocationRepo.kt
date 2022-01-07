package com.rogoz208.notesv2.domain.repos

import com.rogoz208.notesv2.domain.entities.NoteLocationEntity

interface NoteLocationRepo {

    fun getCurrentLocation(callback: (NoteLocationEntity) -> Unit)
}