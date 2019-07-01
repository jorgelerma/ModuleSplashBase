package com.globant.equattrocchio.cleanarchitecture.mvp.model

import com.globant.equattrocchio.cleanarchitecture.ImagesViewMapperImpl
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import com.globant.equattrocchio.domain.SearchImagesUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImagesModel @Inject constructor(private val serviceRequest: GetLatestImagesUseCase,
                                      private val searchRequest: SearchImagesUseCase,
                                      private val imagesMapper: ImagesViewMapperImpl) : ImagesContract.Model {

    override fun serviceRequestCall(): Observable<ResultViewModel> {
        return serviceRequest.getLatestImages()
                .map { resp -> imagesMapper.mapDomainModelToPresentationModel(resp) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchServiceRequestCall(searchQuery: String): Observable<ResultViewModel> {
        return searchRequest.searchImages(searchQuery)
                .map { resp -> imagesMapper.mapDomainModelToPresentationModel(resp) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
