package com.globant.equattrocchio.cleanarchitecture.di.module

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.data.IImageMapperContract
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.ImagesRepository
import com.globant.equattrocchio.data.service.api.IImagesApi
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.service.ImagesServices
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class ImagesContractModule {
    @Binds
    @Reusable
    abstract fun provideImagesMapper(imagesMapper: ImageMapper): IImageMapperContract

    @Binds
    @Reusable
    abstract fun provideImagesApi(imagesApi: ImagesApi): IImagesApi

    @Binds
    @Reusable
    abstract fun provideImagesRepository(imagesRepository: ImagesRepository): ImagesServices

    @Binds
    @Reusable
    abstract fun provideImagesView(imagesView: ImagesView): ImagesContract.View

    @Binds
    @Reusable
    abstract fun provideImagesModel(imagesModel: ImagesModel): ImagesContract.Model

    @Binds
    @Reusable
    abstract fun provideImagesPresenter(imagesView: ImagesPresenter): ImagesContract.Presenter
}