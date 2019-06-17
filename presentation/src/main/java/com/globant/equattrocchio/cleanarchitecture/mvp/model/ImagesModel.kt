package com.globant.equattrocchio.cleanarchitecture.mvp.model

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImagesModel(private val getLatestImagesUseCase: GetLatestImagesUseCase) : ImagesContract.Model {

    override fun serviceRequestCall(): Observable<ResultDomainInput> {
        return getLatestImagesUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
