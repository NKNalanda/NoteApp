package com.nkdroid.core.repository

import com.nkdroid.core.data.Note

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

class NoteRepository(private val dataSource: NoteDataSource) {

    suspend fun addNote(note: Note) = dataSource.add(note)

    suspend fun getNote(id: Long) = dataSource.get(id)

    suspend fun getAllNotes() = dataSource.getAll()

    suspend fun removeNote(note: Note) = dataSource.remove(note)
}