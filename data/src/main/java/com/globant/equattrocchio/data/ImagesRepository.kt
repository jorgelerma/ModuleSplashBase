package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImagesServices
import io.reactivex.Observable

class ImagesRepository(private val mapper: ImageMapper, private val imagesApi: ImagesApi) : ImagesServices {

    override fun getLatestImagesRx(): Observable<ResultDomainInput> {
        return imagesApi.getInstance()!!.getTheLatestImages()
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
    }

    override fun getLatestImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
