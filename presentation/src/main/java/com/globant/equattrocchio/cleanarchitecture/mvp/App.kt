package com.globant.equattrocchio.cleanarchitecture.mvp

import android.app.Activity
import android.app.Application
import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        DaggerAppComponent
                .builder()
                .create(this)
                .inject(this)
    }
}
