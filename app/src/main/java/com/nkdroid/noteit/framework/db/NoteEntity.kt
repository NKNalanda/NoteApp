package com.nkdroid.noteit.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nkdroid.core.data.Note

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

@Entity(tableName = "note")
data class NoteEntity(
        val title: String,
        val content: String,

        @ColumnInfo(name = "creation_date")
        val creationTime: Long,

        @ColumnInfo(name = "update_time")
        val updateTime: Long,

        @PrimaryKey(autoGenerate = true)
        val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(
                note.title,
                note.content,
                note.creationTime,
                note.updateTime,
                note.id
        )
    }

    fun toNote() = Note(title, content, creationTime, updateTime, id)
}