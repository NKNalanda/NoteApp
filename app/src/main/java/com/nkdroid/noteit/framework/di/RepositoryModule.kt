package com.nkdroid.noteit.framework.di

import android.app.Application
import com.nkdroid.core.repository.NoteRepository
import com.nkdroid.noteit.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

@Module
class RepositoryModule {

    @Provides
    fun providesRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}