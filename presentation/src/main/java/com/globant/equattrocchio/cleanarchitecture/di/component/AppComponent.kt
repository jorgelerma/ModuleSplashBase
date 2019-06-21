package com.globant.equattrocchio.cleanarchitecture.di.component

import android.app.Application
import com.globant.equattrocchio.cleanarchitecture.di.module.ActivityModule
import com.globant.equattrocchio.cleanarchitecture.di.module.NetworksModules
import com.globant.equattrocchio.cleanarchitecture.mvp.App
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.service.api.ImagesApi
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//@Component(dependencies = [(AppComponent::class)])
//@Singleton

@Component(
        modules = [
            NetworksModules::class,
            ActivityModule::class,
            AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

//    fun getImagesApi(): ImagesApi
//
//    fun getImageMapper(): ImageMapper
//
//    fun getImageModel(): ImagesModel

}
