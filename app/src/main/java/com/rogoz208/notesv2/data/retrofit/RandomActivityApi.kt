package com.rogoz208.notesv2.data.retrofit

import com.rogoz208.notesv2.domain.entities.RandomActivityEntity
import retrofit2.Call
import retrofit2.http.GET

interface RandomActivityApi {
    @GET("activity")
    fun loadRandomActivity(): Call<RandomActivityEntity>
}