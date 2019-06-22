package com.globant.equattrocchio.cleanarchitecture.mvp

import android.app.Activity
import android.app.Application
import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent
//import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent
//import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
//import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class App: Application(), HasActivityInjector {

//class App: Application() {


    @Inject
    lateinit var activityAndroidInjector: DispatchingAndroidInjector<Activity>
//    lateinit var activityAndroidInjector: DispatchingAndroidInjector<Activity>


    @Override
    override fun onCreate() {
        super.onCreate()
//            DaggerAppComponent.builder()
//                    .application(this)
//                    .build()
//                    .inject(this)

        DaggerAppComponent.builder()
                .create(this)
                .inject(this)

//        DaggerAppComponent.builder().application(this).build().inject(this)
//        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityAndroidInjector
    }

//    override fun activityInjector(): AndroidInjector<Activity> {
//        return activityAndroidInjector
//    }

//    private fun getActivityComponent(): AppComponent {
//        return DaggerAppComponent.builder().build()
//    }
}