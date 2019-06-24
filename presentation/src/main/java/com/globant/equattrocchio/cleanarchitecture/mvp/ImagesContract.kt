package com.globant.equattrocchio.cleanarchitecture.mvp

import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.Observable

interface ImagesContract {
    interface Presenter {
        fun showResponse(response: ResultDomainInput)
        fun callImages()
        fun disposeObserver()
    }

    interface Model {
        fun serviceRequestCall(): Observable<ResultDomainInput>
    }

    interface View {
        fun showError()
        fun showResult(input: ResultDomainInput)
        fun setStatusSubject(status: Boolean)
    }
}
