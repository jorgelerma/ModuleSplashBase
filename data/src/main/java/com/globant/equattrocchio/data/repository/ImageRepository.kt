package com.globant.equattrocchio.data.repository

import android.util.Log
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.models.ResultDataModel
import com.globant.equattrocchio.data.repository.mappers.ImageCacheMapperImpl
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.models.ResultDomainModel
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class ImageRepository @Inject constructor(private val mapper: ImageMapper,
                                          private val imagesApi: ImagesApi,
                                          private val mapperCache: ImageCacheMapperImpl) : BaseDataSource(), ImageService {

    override fun getLatestImages(): Observable<ResultDomainModel> {

        var imagesResponse: Observable<ResultDataModel> = imagesApi.getInstance().getLatestImages()
        val imagesCachedList = getAll()
        lateinit var mappedCachedToData: ResultDataModel

        if(imagesCachedList.isNotEmpty()){
            Log.v(this.javaClass.simpleName, "***** CACHE NOT Empty: ")

            mappedCachedToData = mapperCache.mapCacheModelToDataModel(imagesCachedList[0])
            imagesResponse.startWith(mappedCachedToData)
        }

        return imagesResponse.map { resp -> mapper.mapDataModelToDomainModel(resp)}
                .doOnNext {
                    persistData(mapperCache.mapDomainModelToCacheModel(it))
                }
                .onErrorResumeNext { throwable: Throwable ->
                    Log.e(this.javaClass.simpleName, "*** onErrror ${throwable.message}")
//                    Observable.error(throwable)
                    Observable.just(mapper.mapDataModelToDomainModel(mappedCachedToData))
                }
    }

    override fun searchImages(searchQuery: String): Observable<ResultDomainModel> {
        var imagesResponse: Observable<ResultDataModel> = imagesApi.getInstance().searchQueryImage(searchQuery)
        val imagesCachedList = getAll()
        lateinit var mappedCachedToData: ResultDataModel

        if(imagesCachedList.isNotEmpty()){
            Log.v(this.javaClass.simpleName, "***** CACHE NOT Empty: ")

            mappedCachedToData = mapperCache.mapCacheModelToDataModel(imagesCachedList[0])
            imagesResponse.startWith(mappedCachedToData)
        }

        return imagesResponse.map { resp -> mapper.mapDataModelToDomainModel(resp)}
                .doOnNext {
                    persistData(mapperCache.mapDomainModelToCacheModel(it))
                }
                .onErrorResumeNext { throwable: Throwable ->
                    Log.e(this.javaClass.simpleName, "*** onErrror ${throwable.message}")
//                    Observable.error(throwable)
                    Observable.just(mapper.mapDataModelToDomainModel(mappedCachedToData))
                }
    }

//    override fun searchImages(searchQuery: String): Observable<ResultDomainModel> {
//        var imagesResponse: Observable<ResultDataModel> = imagesApi.getInstance().searchQueryImage(searchQuery)
//        val imagesCachedList = getAll()
//
//        if(imagesCachedList.isNotEmpty()){
//            Log.v(this.javaClass.simpleName, "***** CACHE NOT Empty: ")
//
////            val mappedCachedToDomain: ResultDomainModel = mapperCache.mapCacheModelToDomainModel(imagesCachedList[0])
//            val mappedCachedToDomain: ResultDataModel= mapperCache.mapCacheModelToDataModel(imagesCachedList[0])
//
//            imagesResponse.doOnNext { mappedCachedToDomain}
//
//        }else if(imagesCachedList.isEmpty()){
//            Log.v(this.javaClass.simpleName, "***** CACHE IS Empty: ")
//
//            val mappedCacheList =
//                    imagesResponse.map { resp -> mapperCache.mapDataModelToCacheModel(resp) }
//            save(mappedCacheList)
//        }
//        Log.v(this.javaClass.simpleName, "***** MAKES REQUEST EITHER WAYS: ")
//
//        return imagesResponse.map {
//            resp -> mapper.mapDataModelToDomainModel(resp)}
//    }
}
