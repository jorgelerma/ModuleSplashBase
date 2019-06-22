package com.globant.equattrocchio.cleanarchitecture.di.module

import com.globant.equattrocchio.cleanarchitecture.di.ActivityScope
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.AnotherView
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
        includes = [
            NetworksModules::class
        ]
)
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @Binds
    abstract fun providesImageView(imagesView: AnotherView) : ImagesContract.View

    @Binds
    abstract fun provideImagePresener(imagesPresenter: ImagesPresenter): ImagesContract.Presenter

    @Binds
    abstract fun provideImageModel(imagesModel: ImagesModel): ImagesContract.Model

}
