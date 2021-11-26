package com.rogoz208.notesv2.domain.repos

import androidx.annotation.WorkerThread

interface UrlPreviewRepo {
    @WorkerThread
    fun getWebPageSync(urlString: String): String

    fun getWebPageAsync(urlString: String, callback: (String) -> Unit)
}