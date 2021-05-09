package com.nkdroid.core.data

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

data class Note(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0,
    var wordCount: Int = 0
)
