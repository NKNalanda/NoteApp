package com.nkdroid.core.repository

import com.nkdroid.core.data.Note

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

interface NoteDataSource {
    suspend fun add(note: Note)

    suspend fun get(id: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun remove(note: Note)
}