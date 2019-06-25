package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.service.api.ImagesApiImpl
import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageRepository @Inject constructor(private val mapper: ImageMapperImpl, private val imagesApi: ImagesApiImpl) : ImageService {

    override fun getLatestImages(): Observable<ResultDomainInput> {
      return imagesApi.getInstance().getLatestImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              .map { resp -> mapper.mapDataModelToDomainModel(resp) }
    }

    override fun searchImages(searchQuery: String): Observable<ResultDomainInput> {
        return imagesApi.getInstance().searchQueryImage(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
    }
}
