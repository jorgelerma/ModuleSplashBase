package com.globant.equattrocchio.cleanarchitecture.mvp.model

import com.globant.equattrocchio.cleanarchitecture.ImagesViewMapperImpl
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import com.globant.equattrocchio.domain.SearchImagesUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ImagesModel @Inject constructor(private val serviceRequest: GetLatestImagesUseCase,
                                      private val searchRequest: SearchImagesUseCase,
                                      private val imageMapper: ImagesViewMapperImpl) : ImagesContract.Model {

    override fun serviceRequestCall(): Observable<ResultViewInput> {
        return serviceRequest.getLatestImages()
                .map { resp -> imageMapper.mapDomainModelToPresentationModel(resp) }
    }

    override fun searchServiceRequestCall(searchQuery: String): Observable<ResultViewInput> {
        return searchRequest.searchImage(searchQuery)
                .map { resp -> imageMapper.mapDomainModelToPresentationModel(resp) }
    }
}
