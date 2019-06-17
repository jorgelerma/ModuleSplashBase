package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ImagesPresenter(private val view: ImagesContract.View,
                      private val model: ImagesContract.Model) : ImagesContract.Presenter {

    lateinit var imagesResponse: Observable<ResultDomainInput>
    private val compositeDiposable = CompositeDisposable()
    val REQUEST_SENT = true
    val REQUEST_COMPLETED = false

    override fun showResponse(response: String) {
        view.showResult(response)
    }

    override fun callImages() {
        imagesResponse = model.serviceRequestCall()
        view.setStatusSubject(REQUEST_SENT)
        compositeDiposable.add(imagesResponse.subscribeWith(object : DisposableObserver<ResultDomainInput>(){
            override fun onComplete() {
                Log.e(this.javaClass.simpleName, "******* onCompletes:")
            }

            override fun onNext(t: ResultDomainInput) {
                view.setStatusSubject(REQUEST_COMPLETED)
                val responseReceived = t.images.toString()
                showResponse(responseReceived)
            }

            override fun onError(e: Throwable) {
                Log.e(this.javaClass.simpleName, "******* onErrors: ")
            }

        }))
    }

    override fun disposeObserver() {
        if(!compositeDiposable.isDisposed){
            compositeDiposable.dispose()
        }
    }
}
