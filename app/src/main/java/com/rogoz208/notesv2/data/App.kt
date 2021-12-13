package com.rogoz208.notesv2.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.data.retrofit.RetrofitRandomActivityRepoImpl
import com.rogoz208.notesv2.data.room.NoteDao
import com.rogoz208.notesv2.data.room.NoteRoomDb
import com.rogoz208.notesv2.data.room.RoomNotesRepoImpl
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.RandomActivityRepo

private const val DB_PATH = "notes.db"
private const val SHARED_PREF_NAME = "SHARED_PREF_NAME"

class App : Application() {
    private val noteDb: NoteRoomDb by lazy {
        Room.databaseBuilder(
            this,
            NoteRoomDb::class.java,
            DB_PATH
        ).build()
    }
    private val noteDao: NoteDao by lazy { noteDb.noteDao() }
    val notesRepo: NotesRepo by lazy { RoomNotesRepoImpl(noteDao) }
    val randomActivityRepo: RandomActivityRepo by lazy { RetrofitRandomActivityRepoImpl() }
    val analytics: MyAnalytics by lazy { MyAnalytics(this) }
    val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            SHARED_PREF_NAME,
            MODE_PRIVATE
        )
    }
}

val Context.app: App
    get() = applicationContext as App
