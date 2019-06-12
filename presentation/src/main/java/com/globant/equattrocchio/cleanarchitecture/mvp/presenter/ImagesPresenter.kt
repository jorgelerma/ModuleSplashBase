package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver
import com.globant.equattrocchio.data.ImagesServicesImpl
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class ImagesPresenter(private val view: ImagesView, private val getLatestImagesUseCase: GetLatestImagesUseCase) {

    fun onCallServiceButtonPressed() {

        getLatestImagesUseCase.execute(object : DisposableObserver<Boolean>(){
            override fun onComplete() {
//                ImagesServicesImpl().getLatestImages(object :Observer<Boolean>{
//                    override fun onSubscribe(d: Disposable) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onNext(t: Boolean) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onError(e: Throwable) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onComplete() {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                })
            }

            override fun onNext(t: Boolean) {
                loadFromPreferences()
            }

            override fun onError(e: Throwable) {
                view.showError()
            }
        }, null)
    }

    fun loadFromPreferences(){
        // view.showText("EL TEXTO QUE ME TRAGIA DE LAS PREFERENCES");
    }

    fun register(){
        val actvivity = view.getActivity() ?: return

        RxBus.subscribe(actvivity, object : CallServiceButtonObserver(){
            override fun onEvent(value: CallServiceButtonPressed?) {
                onCallServiceButtonPressed()
            }
        })
    }

    fun unregister(){
        val activity = view.getActivity() ?: return
        RxBus.clear(activity)
    }
}
