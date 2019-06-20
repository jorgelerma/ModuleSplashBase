package com.globant.equattrocchio.cleanarchitecture.mvp

import android.app.Application
import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent

class App: Application() {

    companion object{
//        @JvmStatic lateinit var graphs: AppComponent
    }

    fun initializeDagger(){
        DaggerAppComponent.builder().build().inject(this)
    }

    @Override
    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }
}