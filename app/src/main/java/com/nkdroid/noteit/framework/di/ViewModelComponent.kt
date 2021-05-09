package com.nkdroid.noteit.framework.di

import com.nkdroid.noteit.framework.ListViewModel
import com.nkdroid.noteit.framework.NoteViewModel
import dagger.Component

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}