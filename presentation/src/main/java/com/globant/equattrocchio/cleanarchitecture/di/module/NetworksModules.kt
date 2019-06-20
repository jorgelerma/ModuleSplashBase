package com.globant.equattrocchio.cleanarchitecture.di.module

import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.ImagesRepository
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import dagger.Module
import dagger.Provides

@Module
class NetworksModules {

    @Provides
    fun provideImageApiInstance(): ImagesApi = ImagesApi()

    @Provides
    fun provideImageMapper(): ImageMapper = ImageMapper()

    @Provides
    fun provideImageRepository(): ImagesRepository = ImagesRepository(provideImageMapper(), provideImageApiInstance())

    @Provides
    fun providesGetLatestImageUseCase(): GetLatestImagesUseCase = GetLatestImagesUseCase(provideImageRepository())

    @Provides
    fun provideImageModel(): ImagesModel = ImagesModel(providesGetLatestImageUseCase())

}