package com.globant.equattrocchio.cleanarchitecture.mvp

import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import io.reactivex.Observable

interface ImagesContract {
    interface Presenter {
        fun showImage(response: ResultViewModel)
        fun requestLatestImages()
        fun searchImages(searchQuery: String)
        fun disposeObserver()
    }

    interface Model {
        fun serviceRequestCall(): Observable<ResultViewModel>
        fun searchServiceRequestCall(searchQuery: String): Observable<ResultViewModel>
    }

    interface View {
        fun showError()
        fun showImage(image: ResultViewModel)
        fun setStatusSubject(status: Boolean)
    }
}
