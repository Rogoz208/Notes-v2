package com.rogoz208.notesv2.ui.screens.reminders.edit

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.NOTIFICATION_CONTENT_EXTRA
import com.rogoz208.notesv2.data.NOTIFICATION_TITLE_EXTRA
import com.rogoz208.notesv2.data.ReminderBroadcastReceiver
import com.rogoz208.notesv2.data.app
import com.rogoz208.notesv2.databinding.ActivityEditReminderBinding
import com.rogoz208.notesv2.domain.entities.ReminderEntity
import com.rogoz208.notesv2.ui.screens.notes.edit.EditNoteActivity
import java.text.SimpleDateFormat
import java.util.*

class EditReminderActivity : AppCompatActivity(R.layout.activity_edit_reminder) {
    companion object {
        const val REMINDER_EXTRA_KEY = "REMINDER_EXTRA_KEY"
        const val REMINDER_POSITION_EXTRA_KEY = "REMINDER_POSITION_EXTRA_KEY"
    }

    private val binding by viewBinding(ActivityEditReminderBinding::bind)

    private val viewModel: EditReminderViewModel by viewModels { EditReminderViewModelFactory(app.remindersRepo) }

    private var reminder: ReminderEntity? = null
    private var remindTime: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initToolbar()
        fillViews()
        setupListeners()
    }

    override fun onBackPressed() {
        saveReminder()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initViewModel() {
        viewModel.reminderSavedLiveData.observe(this) { isReminderSaved ->
            viewModel.reminderEntityLiveData.value?.let {
                createReminderNotificationAlarm(it)
            }
            if (isReminderSaved) {
                val intent = Intent()
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun createReminderNotificationAlarm(reminder: ReminderEntity) {
        val intent = Intent(this, ReminderBroadcastReceiver::class.java)
        intent.putExtra(NOTIFICATION_TITLE_EXTRA, reminder.title)
        intent.putExtra(NOTIFICATION_CONTENT_EXTRA, reminder.detail)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        alarmManager.set(AlarmManager.RTC_WAKEUP, reminder.remindTime, pendingIntent)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun fillViews() {
        reminder = intent.getParcelableExtra(REMINDER_EXTRA_KEY)
        reminder?.let { reminder ->
            binding.titleEditText.setText(reminder.title)
            binding.detailEditText.setText(reminder.detail)
            binding.reminderTimeEditText.setText(reminder.remindTime.toString())
        }
    }

    private fun setupListeners() {
        binding.reminderTimeEditText.setOnClickListener {
            showDateTimeDialog(binding.reminderTimeEditText)
        }
    }

    private fun saveReminder() {
        var position: Int? = null
        if (intent.hasExtra(REMINDER_POSITION_EXTRA_KEY)) {
            position = intent.getIntExtra(REMINDER_POSITION_EXTRA_KEY, 0)
        }
        viewModel.onReminderSaved(
            reminder,
            binding.titleEditText.text.toString(),
            binding.detailEditText.text.toString(),
            dateToLong(binding.reminderTimeEditText.text.toString()),
            position
        )
    }

    private fun dateToLong(dateStr: String): Long {
        val dateFormat = SimpleDateFormat("dd.MM.y HH:mm", Locale.getDefault())
        val date = dateFormat.parse(dateStr)
        return date.time
    }

    private fun showDateTimeDialog(reminderTimeEditText: EditText) {
        val calendar = Calendar.getInstance()
        val dateSetListener =
            OnDateSetListener { datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val timeSetListener =
                    OnTimeSetListener { timePicker: TimePicker?, hourOfDay: Int, minute: Int ->
                        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
                        calendar[Calendar.MINUTE] = minute
                        remindTime = calendar.timeInMillis
                        val date = Date(remindTime!!)
                        val dateFormat = SimpleDateFormat("dd.MM.y HH:mm", Locale.getDefault())
                        reminderTimeEditText.setText(dateFormat.format(date))
                    }
                TimePickerDialog(
                    this,
                    timeSetListener,
                    calendar[Calendar.HOUR_OF_DAY],
                    calendar[Calendar.MINUTE],
                    true
                ).show()
            }
        DatePickerDialog(
            this,
            dateSetListener,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }
}