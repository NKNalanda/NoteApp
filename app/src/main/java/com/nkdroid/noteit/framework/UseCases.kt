package com.nkdroid.noteit.framework

import com.nkdroid.core.usecase.*

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
)