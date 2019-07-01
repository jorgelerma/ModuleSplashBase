package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.utils.Constants.REQUEST_COMPLETED
import com.globant.equattrocchio.cleanarchitecture.utils.Constants.REQUEST_SENT
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ImagesPresenter @Inject constructor(private val view: ImagesContract.View, private val model: ImagesContract.Model) : ImagesContract.Presenter {

    private lateinit var imagesResponse: Observable<ResultViewModel>
    private val compositeDiposable = CompositeDisposable()

    override fun requestLatestImages() {
        imagesResponse = model.serviceRequestCall()
        view.setStatusSubject(REQUEST_SENT)

        compositeDiposable.add(imagesResponse.subscribeWith(object : DisposableObserver<ResultViewModel>() {

            override fun onComplete() {
            }

            override fun onNext(response: ResultViewModel) {
                view.setStatusSubject(REQUEST_COMPLETED)
                for(res in response.images){
                    Log.v(this.javaClass.simpleName, "**** ${res.url}")

                }
                updateImagesList(response)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }))
    }

    override fun searchImages(searchQuery: String) {
        imagesResponse = model.searchServiceRequestCall(searchQuery)
        view.setStatusSubject(REQUEST_SENT)
        compositeDiposable.add(imagesResponse.subscribeWith(object : DisposableObserver<ResultViewModel>() {

            override fun onComplete() {
            }

            override fun onNext(response: ResultViewModel) {
                view.setStatusSubject(REQUEST_COMPLETED)
                updateImagesList(response)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }))
    }

    override fun disposeObserver() {
        if (!compositeDiposable.isDisposed) {
            compositeDiposable.dispose()
        }
    }

    override fun initAdapter(){
        view.initAdapter()
    }

    override fun updateImagesList(response: ResultViewModel){
        view.updateImagesList(response.images)
    }
}
