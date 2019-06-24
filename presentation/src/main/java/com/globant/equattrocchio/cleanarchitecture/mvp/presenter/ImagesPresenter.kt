package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.utils.Constants.REQUEST_COMPLETED
import com.globant.equattrocchio.cleanarchitecture.utils.Constants.REQUEST_SENT
import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ImagesPresenter @Inject constructor(private val view : ImagesContract.View,  private val model: ImagesContract.Model) : ImagesContract.Presenter {

    private lateinit var imagesResponse: Observable<ResultDomainInput>
    private val compositeDiposable = CompositeDisposable()

    override fun showResponse(response: ResultDomainInput) {
        view.showResult(response)
    }

    override fun callImages() {
        imagesResponse = model.serviceRequestCall()
        view.setStatusSubject(REQUEST_SENT)
        if (imagesResponse != null) {
            compositeDiposable.add(imagesResponse.subscribeWith(object : DisposableObserver<ResultDomainInput>() {

                override fun onComplete() {
                }

                override fun onNext(response: ResultDomainInput) {
                    view.setStatusSubject(REQUEST_COMPLETED)
                    showResponse(response)
                }

                override fun onError(e: Throwable) {
                }

            }))
        }
    }

    override fun disposeObserver() {
        if(!compositeDiposable.isDisposed){
            compositeDiposable.dispose()
        }
    }
}
