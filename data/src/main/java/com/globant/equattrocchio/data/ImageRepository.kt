package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.models.ResultDomainModel
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageRepository @Inject constructor(private val mapper: ImageMapper, private val imagesApi: ImagesApi) : ImageService {

    override fun getLatestImages(): Observable<ResultDomainModel> {
        return imagesApi.getInstance().getLatestImages()
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchImages(searchQuery: String): Observable<ResultDomainModel> {
        return imagesApi.getInstance().searchQueryImage(searchQuery)
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
