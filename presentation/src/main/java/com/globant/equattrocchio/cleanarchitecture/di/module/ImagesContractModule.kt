package com.globant.equattrocchio.cleanarchitecture.di.module

import com.globant.equattrocchio.cleanarchitecture.ImagesViewMapper
import com.globant.equattrocchio.cleanarchitecture.ImagesViewMapperImpl
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.ImageMapperImpl
import com.globant.equattrocchio.data.ImageRepository
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.data.service.api.ImagesApiImpl
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import com.globant.equattrocchio.domain.ImageUseCasesContract
import com.globant.equattrocchio.domain.SearchImagesUseCase
import com.globant.equattrocchio.domain.service.ImageService
import dagger.Binds
import dagger.Module
import dagger.Reusable
import javax.inject.Singleton

@Module(includes = [
    SubjectsModule::class
])
abstract class ImagesContractModule {

    @Binds
    @Reusable
    abstract fun provideImagesMapper(imagesMapper: ImageMapperImpl): ImageMapper

    @Binds
    @Reusable
    abstract fun provideImagesApi(imagesApi: ImagesApiImpl): ImagesApi

    @Binds
    @Reusable
    abstract fun provideImagesRepository(imagesRepository: ImageRepository): ImageService

    @Binds
    @Reusable
    abstract fun provideImagesView(imagesView: ImagesView): ImagesContract.View

    @Binds
    @Reusable
    abstract fun provideImagesModel(imagesModel: ImagesModel): ImagesContract.Model

    @Binds
    @Reusable
    abstract fun provideImagesPresenter(imagesView: ImagesPresenter): ImagesContract.Presenter

    @Binds
    @Reusable
    abstract fun provideGetLatestImagesUseCase(getLatestImagesUseCase: GetLatestImagesUseCase): ImageUseCasesContract.GetLatestImagesContract

    @Binds
    @Reusable
    abstract fun provideSearchImagesUseCase(searchImagesUseCase: SearchImagesUseCase): ImageUseCasesContract.SearchImagesContract

    @Binds
    @Reusable
    abstract fun provideImageViewMapper(imagesViewMapper: ImagesViewMapperImpl): ImagesViewMapper
}
