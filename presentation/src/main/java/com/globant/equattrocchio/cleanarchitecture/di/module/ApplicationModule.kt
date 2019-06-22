package com.globant.equattrocchio.cleanarchitecture.di.module

import android.content.Context
import com.globant.equattrocchio.cleanarchitecture.mvp.App
import dagger.Binds
import dagger.Module

@Module(includes = [
    ActivityModule::class,
    NetworksModules::class
])
abstract class ApplicationInjectorsModule {
    @Binds
    internal abstract fun provideContext(application: App): Context
}