package com.rogoz208.notesv2.ui.screens.reminders.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.ActivityEditReminderBinding

class EditReminderActivity : AppCompatActivity(R.layout.activity_edit_reminder) {

    companion object {
        const val REMINDER_EXTRA_KEY = "REMINDER_EXTRA_KEY"
        const val REMINDER_POSITION_EXTRA_KEY = "REMINDER_POSITION_EXTRA_KEY"
    }

    private val binding by viewBinding(ActivityEditReminderBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}