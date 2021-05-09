package com.nkdroid.core.usecase

import com.nkdroid.core.data.Note

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

class GetWordCount {
    operator fun invoke(note: Note) = getCount(note.title) + getCount(note.content)

    private fun getCount(str: String) = str.split(" ", "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()
}