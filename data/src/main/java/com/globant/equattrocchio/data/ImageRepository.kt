package com.globant.equattrocchio.data

import android.util.Log
import com.globant.equattrocchio.data.service.api.ImagesApiImpl
import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImagesServices
import io.reactivex.Observable
import javax.inject.Inject

class ImagesRepository @Inject constructor(private val mapper: ImageMapper, private val imagesApi: ImagesApiImpl) : ImagesServices {


    override fun getLatestImagesRx(): Observable<ResultDomainInput> {
        Log.e(this.javaClass.simpleName, "getLatestImagesRx invoked: ")
        return imagesApi.getInstance().getTheLatestImages()
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
    }

    override fun getLatestImages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
