package com.nkdroid.core.usecase

import com.nkdroid.core.repository.NoteRepository

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

class GetNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}