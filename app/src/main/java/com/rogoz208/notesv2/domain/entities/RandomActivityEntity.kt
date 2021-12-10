package com.rogoz208.notesv2.domain.entities

import com.google.gson.annotations.SerializedName

data class RandomActivityEntity(
    @SerializedName("activity")
    val activity: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("link")
    val link: String
)