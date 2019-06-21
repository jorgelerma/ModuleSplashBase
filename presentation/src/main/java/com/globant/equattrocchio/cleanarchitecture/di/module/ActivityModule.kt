package com.globant.equattrocchio.cleanarchitecture.di.module

import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}
