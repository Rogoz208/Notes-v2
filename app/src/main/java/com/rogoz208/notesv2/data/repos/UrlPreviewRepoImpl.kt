package com.rogoz208.notesv2.data.repos

import android.os.Build
import androidx.annotation.RequiresApi
import com.rogoz208.notesv2.domain.repos.UrlPreviewRepo
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

class UrlPreviewRepoImpl : UrlPreviewRepo {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getWebPageSync(urlString: String): String {
        val result: String
        val url = URL(urlString)
        var connection: HttpURLConnection? = null
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5_000

            val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
            result = bufferedReader.lines().collect(Collectors.joining("\n"))
        } finally {
            connection?.disconnect()
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getWebPageAsync(urlString: String, callback: (String) -> Unit) {
        Thread {
            callback.invoke(getWebPageSync(urlString))
        }.start()
    }
}