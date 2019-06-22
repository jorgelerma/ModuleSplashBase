package com.globant.equattrocchio.cleanarchitecture.di.module

import com.globant.equattrocchio.cleanarchitecture.di.ActivityScope
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.HasActivityInjector

@Module(
        includes = [
            NetworksModules::class
        ]
)
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun providesImageView(imagesView: ImagesView) : ImagesContract.View

    @Binds
    abstract fun provideImagePresener(imagesPresenter: ImagesPresenter): ImagesContract.Presenter

    @Binds
    abstract fun provideImageModel(imagesModel: ImagesModel): ImagesContract.Model


//    @Binds
//    abstract fun provideImageApiInstance(imagesApi: ImagesApi): ImagesApi
//
//    @Binds
//    abstract fun provideImageMapper(imagesMapper: ImageMapper): ImageMapper
//
//    @Binds
//    abstract fun provideImageRepository(imagesRepository: ImagesRepository): ImagesRepository
//
//    @Binds
//    abstract fun providesGetLatestImageUseCase(getLatestImagesUseCase: GetLatestImagesUseCase): GetLatestImagesUseCase
//
//    @Binds
//    abstract fun provideImageModel(imagesModel: ImagesModel): ImagesModel
//
//    @Binds
//    abstract fun provideImageView(imageView: ImageView) : ImagesView

//    @Provides
//    fun providesImagesView(): ImagesView = ImagesView(contributeMainActivity())

//    @Provides
//    fun providesImagesPresenter(): ImagesPresenter = ImagesPresenter(providesImagesView(), provideImageModel())
}
