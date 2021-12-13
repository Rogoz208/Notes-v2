package com.rogoz208.notesv2.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.app
import com.rogoz208.notesv2.databinding.ActivityMainBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.ui.screens.notes.list.NotesListFragment
import com.rogoz208.notesv2.ui.screens.reminders.RemindersFragment
import com.rogoz208.notesv2.ui.screens.settings.SettingsFragment

private const val IS_FIRST_START_PREF_KEY = "IS_FIRST_START_PREF_KEY"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val preferences: SharedPreferences by lazy { app.sharedPreferences }
    private var isFirstStart: Boolean = true

    private val fragmentsMap = mapOf(
        R.id.bottom_menu_item_notes_screen to NotesListFragment(),
        R.id.bottom_menu_item_reminders_screen to RemindersFragment(),
        R.id.bottom_menu_item_settings_screen to SettingsFragment(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fillRepoByTestValues()
        setSupportActionBar(binding.toolbar)
        initBottomNavigation()
        openDefaultScreen(savedInstanceState)
    }

    override fun onStop() {
        preferences.edit().let {
            it.putBoolean(IS_FIRST_START_PREF_KEY, isFirstStart)
            it.commit()
        }
        super.onStop()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            supportFragmentManager
                .beginTransaction()
                .replace(
                    binding.fragmentContainer.id,
                    fragmentsMap[item.itemId]!!
                )
                .commit()
            true
        }
    }

    private fun openDefaultScreen(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.bottom_menu_item_notes_screen
        }
    }

    private fun fillRepoByTestValues() {
        isFirstStart = preferences.getBoolean(IS_FIRST_START_PREF_KEY, true)
        if (isFirstStart) {
            for (i in 1..20) {
                app.notesRepo.createNote(
                    NoteEntity(
                        "",
                        "Заметка $i",
                        "Lorem ipsum dolor sit amet, consectetur " +
                                "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                        null
                    )
                )
            }
            isFirstStart = false
        }
    }
}
