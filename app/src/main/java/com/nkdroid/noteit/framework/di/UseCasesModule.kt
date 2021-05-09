package com.nkdroid.noteit.framework.di

import com.nkdroid.core.repository.NoteRepository
import com.nkdroid.core.usecase.*
import com.nkdroid.noteit.framework.UseCases
import dagger.Module
import dagger.Provides

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

@Module
class UseCasesModule {

    @Provides
    fun providesUseCases(repository: NoteRepository) = UseCases(
            AddNote(repository),
            GetAllNotes(repository),
            GetNote(repository),
            RemoveNote(repository),
            GetWordCount()
    )
}