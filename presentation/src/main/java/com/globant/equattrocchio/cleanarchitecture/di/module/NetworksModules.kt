package com.globant.equattrocchio.cleanarchitecture.di.module

import android.app.Application
import android.content.Context
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.ImagesRepository
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworksModules {

    @Provides
    fun provideImageApiInstance(): ImagesApi = ImagesApi()

    @Provides
    fun provideImageMapper(): ImageMapper = ImageMapper()

    @Provides
    fun provideImageRepository(imagesApi: ImagesApi, imagesMapper: ImageMapper): ImagesRepository = ImagesRepository(imagesMapper, imagesApi)

    @Provides
    fun providesGetLatestImageUseCase(imagesRepository: ImagesRepository): GetLatestImagesUseCase = GetLatestImagesUseCase(imagesRepository)
}
