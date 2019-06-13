package com.globant.equattrocchio.domain

import android.util.Log
import com.globant.equattrocchio.domain.service.ImagesServiceses
import io.reactivex.observers.DisposableObserver

class GetLatestImagesUseCase(private val imagesServices: ImagesServiceses): UseCase<Boolean, Void>() {

    override fun buildUseCaseObservable(observer: DisposableObserver<Boolean>?, params: Void?) {
        Log.e(this.javaClass.simpleName, " @ buildUseCaseObservable impl called: ")
        imagesServices.getLatestImages()
    }
}
