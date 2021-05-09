package com.nkdroid.noteit.framework

import android.content.Context
import com.nkdroid.core.data.Note
import com.nkdroid.core.repository.NoteDataSource
import com.nkdroid.noteit.framework.db.DatabaseService
import com.nkdroid.noteit.framework.db.NoteEntity

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

class RoomNoteDataSource(context: Context): NoteDataSource {
    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}