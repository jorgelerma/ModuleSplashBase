package com.globant.equattrocchio.cleanarchitecture.mvp

import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import io.reactivex.Observable

interface ImagesContract {
    interface Presenter {
        fun requestLatestImages()
        fun searchImages(searchQuery: String)
        fun disposeObserver()
        fun initAdapter()
        fun updateImagesList(response: ResultViewModel)
    }

    interface Model {
        fun serviceRequestCall(): Observable<ResultViewModel>
        fun searchServiceRequestCall(searchQuery: String): Observable<ResultViewModel>
    }

    interface View {
        fun showError()
        fun setStatusSubject(status: Boolean)
        fun initAdapter()
        fun updateImagesList(imagesList: List<ImageModel>)
    }
}
