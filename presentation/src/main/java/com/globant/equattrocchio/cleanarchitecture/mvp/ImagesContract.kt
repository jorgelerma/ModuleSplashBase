package com.globant.equattrocchio.cleanarchitecture.mvp

import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import io.reactivex.Observable

interface ImagesContract {
    interface Presenter {
        fun showResponse(response: ResultViewInput)
        fun showImage(response: ResultViewInput)
        fun requestLatestImages()
        fun searchImages(searchQuery: String)
        fun disposeObserver()
    }

    interface Model {
        fun serviceRequestCall(): Observable<ResultViewInput>
        fun searchServiceRequestCall(searchQuery: String): Observable<ResultViewInput>
    }

    interface View {
        fun showError()
        fun showResult(input: ResultViewInput)
        fun setStatusSubject(status: Boolean)
        fun showImage(image: ResultViewInput)
        fun showImage()
    }
}
