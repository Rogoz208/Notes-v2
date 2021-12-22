package com.rogoz208.notesv2.ui.screens.reminders.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.ActivityEditReminderBinding

class EditReminderActivity : AppCompatActivity(R.layout.activity_edit_reminder) {

    private val binding by viewBinding(ActivityEditReminderBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}