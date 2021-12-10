package com.rogoz208.notesv2.domain.repos

import com.rogoz208.notesv2.domain.entities.RandomActivityEntity

interface RandomActivityRepo {

    fun getRandomActivitySync(): RandomActivityEntity

    fun getRandomActivityAsync(
        onSuccess: (RandomActivityEntity) -> Unit,
        onError: (Throwable) -> Unit
    )
}