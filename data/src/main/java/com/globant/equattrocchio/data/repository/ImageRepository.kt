package com.globant.equattrocchio.data.repository

import android.util.Log
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.models.ResultDataModel
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import com.globant.equattrocchio.data.repository.mappers.ImageCacheMapperImpl
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.models.ResultDomainModel
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageRepository @Inject constructor(private val mapper: ImageMapper,
                                          private val imagesApi: ImagesApi,
                                          private val mapperCache: ImageCacheMapperImpl) : BaseDataSource(), ImageService {

    override fun getLatestImages(): Observable<ResultDomainModel> {

        var imagesResponse: Observable<ResultDataModel> = imagesApi.getInstance().getLatestImages()

        lateinit var imagesMappedResponse: Observable<ResultDomainModel>
        val imagesCachedList = getAll()

        if(imagesCachedList != null){
            Log.d(this.javaClass.simpleName, "***** @elseif when cacheList not Empty: ")

            val mappedCachedToDomain = mapperCache.mapCacheModelToDomainModel(imagesCachedList)
            imagesMappedResponse = Observable.just(mappedCachedToDomain)
            //*****************

        }else {
            Log.d(this.javaClass.simpleName, "***** Cache Empty: ")
            val mappedCacheList =
                    imagesResponse.map { resp -> mapperCache.mapServiceModelToCacheModel(resp) }

            imagesMappedResponse = imagesResponse.map {
                resp -> mapper.mapDataModelToDomainModel(resp)
            }

//            mappedCacheList.map { this.save(it) }
            mappedCacheList
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe { it -> save(it) }
        }
        return imagesMappedResponse
    }

    override fun searchImages(searchQuery: String): Observable<ResultDomainModel> {
        return imagesApi.getInstance().searchQueryImage(searchQuery)
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun save(input: ResultCacheModel){
        Log.d(this.javaClass.simpleName, "***** Child save invoked: ")
        super.save(input)

    }
}
