package com.globant.equattrocchio.cleanarchitecture.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Singleton

@Module
class SubjectsModule{

    @Singleton
    @Provides
    fun provideSubject(): PublishSubject<Boolean>{
        return PublishSubject.create<Boolean>()
    }
}
