package com.rogoz208.notesv2.ui.screens.settings.log

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.log.MyLogService
import com.rogoz208.notesv2.databinding.FragmentLogBinding
import java.io.*

class LogFragment : Fragment(R.layout.fragment_log) {
    private val binding by viewBinding(FragmentLogBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logTextView.text = readFromFile()
    }

    private fun readFromFile(): String {
        var data = ""
        try {
            val inputStream: InputStream = requireContext().openFileInput(MyLogService.LOG_FILE)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { receiveString = it } != null) {
                stringBuilder.append("\n").append(receiveString)
            }
            inputStream.close()
            data = stringBuilder.toString()
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: $e")
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }
        return data
    }
}
