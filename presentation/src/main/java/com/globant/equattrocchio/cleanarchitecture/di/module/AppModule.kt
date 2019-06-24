package com.globant.equattrocchio.cleanarchitecture.di.module

import android.content.Context
import com.globant.equattrocchio.cleanarchitecture.mvp.App
import dagger.Binds
import dagger.Module

@Module(includes = [
    ApplicationInjectorsModule::class
])
abstract class AppModule {

    @Binds
    internal abstract fun provideContext(application: App) : Context
}
