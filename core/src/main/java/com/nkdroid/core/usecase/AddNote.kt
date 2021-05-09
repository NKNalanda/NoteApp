package com.nkdroid.core.usecase

import com.nkdroid.core.data.Note
import com.nkdroid.core.repository.NoteRepository

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}