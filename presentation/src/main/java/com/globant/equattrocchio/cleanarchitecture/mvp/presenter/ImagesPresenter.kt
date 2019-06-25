package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.utils.Constants.REQUEST_COMPLETED
import com.globant.equattrocchio.cleanarchitecture.utils.Constants.REQUEST_SENT
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ImagesPresenter @Inject constructor(private val view : ImagesContract.View,  private val model: ImagesContract.Model) : ImagesContract.Presenter {

    private lateinit var imagesResponse: Observable<ResultViewInput>
    private val compositeDiposable = CompositeDisposable()

    override fun showResponse(response: ResultViewInput) {
        view.showResult(response)
    }

    override fun showImage(response: ResultViewInput) {
        view.showImage(response)
    }

    override fun requestLatestImages() {
        imagesResponse = model.serviceRequestCall()
        view.setStatusSubject(REQUEST_SENT)

        compositeDiposable.add(imagesResponse.subscribeWith(object : DisposableObserver<ResultViewInput>() {

            override fun onComplete() {
            }

            override fun onNext(response: ResultViewInput) {
                view.setStatusSubject(REQUEST_COMPLETED)
                showImage(response)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        }))
    }

    override fun searchImages(searchQuery: String) {
        imagesResponse = model.searchServiceRequestCall(searchQuery)
        view.setStatusSubject(REQUEST_SENT)
        compositeDiposable.add(imagesResponse.subscribeWith(object : DisposableObserver<ResultViewInput>(){
            
            override fun onComplete() {
                Log.d(this.javaClass.simpleName, " onComplete  @ searchImages: ")
            }

            override fun onNext(response: ResultViewInput) {
                view.setStatusSubject(REQUEST_COMPLETED)
                showImage(response)
            }
            
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        }))
    }

    override fun disposeObserver() {
        if(!compositeDiposable.isDisposed){
            compositeDiposable.dispose()
        }
    }
}
