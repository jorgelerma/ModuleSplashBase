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
        fun showError(error: String)
    }

    interface Model {
        fun serviceRequestCall(): Observable<ResultViewModel>
        fun searchServiceRequestCall(searchQuery: String): Observable<ResultViewModel>
    }

    interface View {
        fun showError(error: String)
        fun initAdapter()
        fun updateImagesList(imagesList: List<ImageModel>)
    }
}
