package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ImagesPresenter @Inject constructor(private val view: ImagesContract.View, private val model: ImagesContract.Model) : ImagesContract.Presenter {

    private var imageDisposable: Disposable? = null

    override fun requestLatestImages() {
        imageDisposable = model.serviceRequestCall()
                .subscribeWith(object : DisposableObserver<ResultViewModel>() {
                    override fun onComplete() {
                    }

                    override fun onNext(response: ResultViewModel) {
                        updateImagesList(response)
                    }

                    override fun onError(error: Throwable) {
                        error.printStackTrace()
                        showError(error.message.toString())
                    }
                })
    }

    override fun searchImages(searchQuery: String) {
        imageDisposable = model.searchServiceRequestCall(searchQuery)
                .subscribeWith(object : DisposableObserver<ResultViewModel>() {
                    override fun onComplete() {
                    }

                    override fun onNext(response: ResultViewModel) {
                        updateImagesList(response)
                    }

                    override fun onError(error: Throwable) {
                        error.printStackTrace()
                        showError(error.message.toString())
                    }
                })
    }

    override fun disposeObserver() {
        imageDisposable?.dispose()
    }

    override fun initAdapter() {
        view.initAdapter()
    }

    override fun updateImagesList(response: ResultViewModel) {
        view.updateImagesList(response.images)
    }

    override fun showError(error: String) {
        view.showError(error)
    }
}
