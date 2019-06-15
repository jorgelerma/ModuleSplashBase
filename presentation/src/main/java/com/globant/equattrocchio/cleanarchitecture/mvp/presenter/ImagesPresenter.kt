package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.observers.DisposableObserver

class ImagesPresenter(private val view: ImagesContract.View,
                      private val model: ImagesContract.Model) : ImagesContract.Presenter {

    override fun showResponse(response: String) {
        view.showResult(response)
    }

    override fun callImages() {
        model.serviceRequestCall()
                .subscribe(object : DisposableObserver<ResultDomainInput>() {
                    override fun onComplete() {
                        Log.e(this.javaClass.simpleName, "******* onCompletes:");
                    }

                    override fun onNext(t: ResultDomainInput) {
                        Log.e(this.javaClass.simpleName, "******  onNext: ")
                        val responseReceived = t.images.toString()
                        showResponse(responseReceived)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(this.javaClass.simpleName, "******* onErrors: ")
                    }
                })
    }
}
