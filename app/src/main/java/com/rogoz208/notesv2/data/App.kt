package com.rogoz208.notesv2.data

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.data.log.NetworkStateReceiver
import com.rogoz208.notesv2.data.retrofit.RetrofitRandomActivityRepoImpl
import com.rogoz208.notesv2.data.room.RoomNotesRepoImpl
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.RandomActivityRepo

class App : Application() {
    val notesRepo: NotesRepo by lazy { RoomNotesRepoImpl(this) }
    val randomActivityRepo: RandomActivityRepo by lazy { RetrofitRandomActivityRepoImpl() }
    val analytics: MyAnalytics by lazy { MyAnalytics(this) }

    private val networkReceiver by lazy { NetworkStateReceiver() }
    private val intentFilterNetwork by lazy { IntentFilter() }
}

val Context.app: App
    get() = applicationContext as App
