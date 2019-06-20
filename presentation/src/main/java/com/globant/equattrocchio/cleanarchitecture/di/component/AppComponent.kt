package com.globant.equattrocchio.cleanarchitecture.di.component

import com.globant.equattrocchio.cleanarchitecture.di.module.NetworksModules
import com.globant.equattrocchio.cleanarchitecture.mvp.App
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.service.api.ImagesApi
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [(NetworksModules::class)])
interface AppComponent {

    fun getImagesApi(): ImagesApi

    fun getImageMapper(): ImageMapper

    fun getImageModel(): ImagesModel

    fun inject(activity: MainActivity)

    fun inject(myApp: App)
}