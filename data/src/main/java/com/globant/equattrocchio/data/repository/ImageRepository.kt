package com.globant.equattrocchio.data.repository

import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.models.ResultDataModel
import com.globant.equattrocchio.data.repository.mappers.ImageCacheMapperImpl
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.models.ResultDomainModel
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imagesApi: ImagesApi,
                                          private val mapper: ImageMapper,
                                          private val mapperCache: ImageCacheMapperImpl) : BaseDataSource(), ImageService {

    override fun getLatestImages(): Observable<ResultDomainModel> {

        val imagesResponse: Observable<ResultDataModel> = imagesApi.getInstance().getLatestImages()
        val imagesCacheList = getAll()
        var imagesCache = ResultDataModel()

        if (imagesCacheList.isNotEmpty()) {
            imagesCache = mapperCache.mapCacheModelToDataModel(imagesCacheList.first())
        }

        return imagesResponse
                .map { resp ->
                    mapper.mapDataModelToDomainModel(resp)
                }
                .doOnNext {
                    saveData(mapperCache.mapDomainModelToCacheModel(it))
                }
                .onErrorResumeNext { throwable: Throwable ->
                    Observable.error(throwable)
                }
                .startWith(mapper.mapDataModelToDomainModel(imagesCache))
    }

    override fun searchImages(searchQuery: String): Observable<ResultDomainModel> {

        val imagesResponse: Observable<ResultDataModel> = imagesApi.getInstance().searchQueryImage(searchQuery)
        val imagesCacheList = getAll()
        var imagesCache = ResultDataModel()

        if (imagesCacheList.isNotEmpty()) {
            imagesCache = mapperCache.mapCacheModelToDataModel(imagesCacheList.first())
        }

        return imagesResponse
                .map { resp ->
                    mapper.mapDataModelToDomainModel(resp)
                }
                .doOnNext {
                    saveData(mapperCache.mapDomainModelToCacheModel(it))
                }
                .onErrorResumeNext { throwable: Throwable ->
                    Observable.error(throwable)
                }
                .startWith(mapper.mapDataModelToDomainModel(imagesCache))
    }
}
