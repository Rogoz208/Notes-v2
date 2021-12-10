package com.rogoz208.notesv2.data

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.data.log.NetworkStateReceiver
import com.rogoz208.notesv2.data.repos.MemoryCacheNotesRepoImpl
import com.rogoz208.notesv2.data.repos.RetrofitRandomActivityRepoImpl
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.RandomActivityRepo

class App : Application() {
    val notesRepo: NotesRepo by lazy { MemoryCacheNotesRepoImpl() }
    val randomActivityRepo: RandomActivityRepo by lazy { RetrofitRandomActivityRepoImpl() }
    val analytics: MyAnalytics by lazy { MyAnalytics(this) }

    private val networkReceiver by lazy { NetworkStateReceiver() }
    private val intentFilterNetwork by lazy { IntentFilter() }

    override fun onCreate() {
        super.onCreate()

        fillRepoByTestValues()
//        registerNetworkReceiver()
    }

    private fun registerNetworkReceiver() {
        intentFilterNetwork.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, intentFilterNetwork)
    }

    private fun fillRepoByTestValues() {
        for (i in 1..20) {
            notesRepo.createNote(
                NoteEntity(
                    null,
                    "Заметка $i",
                    "Lorem ipsum dolor sit amet, consectetur " +
                            "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    null
                )
            )
        }
    }
}

val Context.app: App
    get() = applicationContext as App
