package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.service.api.ImagesApiImpl
import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class ImageRepository @Inject constructor(private val mapper: ImageMapperImpl, private val imagesApi: ImagesApiImpl) : ImageService {

    override fun getLatestImages(): Observable<ResultDomainInput> {
        return imagesApi.getInstance().getLatestImages()
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
    }
}
