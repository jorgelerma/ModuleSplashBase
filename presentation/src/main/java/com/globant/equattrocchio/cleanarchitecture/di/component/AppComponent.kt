package com.globant.equattrocchio.cleanarchitecture.di.component

import com.globant.equattrocchio.cleanarchitecture.di.module.AppModule
import com.globant.equattrocchio.cleanarchitecture.di.module.ApplicationInjectorsModule
import com.globant.equattrocchio.cleanarchitecture.mvp.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            AndroidInjectionModule::class
        ])
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
    }
}
