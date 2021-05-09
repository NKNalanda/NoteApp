package com.nkdroid.noteit.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

@Module
class ApplicationModule(val app: Application) {

    @Provides
    fun providesApp() = app
}