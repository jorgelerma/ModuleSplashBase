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

        var imagesResponse: Observable<ResultDataModel>

        lateinit var imagesMappedResponse: Observable<ResultDomainModel>
        val imagesCachedList = getAll()

        if(imagesCachedList.isNotEmpty()){
            Log.v(this.javaClass.simpleName, "***** @elseif when cacheList not Empty: ")

            for(input in imagesCachedList){
                Log.v(this.javaClass.simpleName, "***** ${input.images}")
            }

            val mappedCachedToDomain: ResultDomainModel = mapperCache.mapCacheModelToDomainModel(imagesCachedList[0])
//            return Observable.just(mappedCachedToDomain)

            Log.v(this.javaClass.simpleName, " ***** ## $mappedCachedToDomain")
//            return Observable.just(mappedCachedToDomain)
            return Observable.create<ResultDomainModel>{
                it -> it.onNext(mappedCachedToDomain)
                it.onComplete()
            }
            //*****************

        }else {
            Log.v(this.javaClass.simpleName, "***** Cache Empty: ")
            imagesResponse = imagesApi.getInstance().getLatestImages()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

//            imagesMappedResponse = imagesResponse.map {
//                resp -> mapper.mapDataModelToDomainModel(resp)
//            }

            return imagesResponse.map {
                resp -> mapper.mapDataModelToDomainModel(resp)
            }

            val mappedCacheList =
                    imagesResponse.map { resp -> mapperCache.mapServiceModelToCacheModel(resp) }

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
        Log.v(this.javaClass.simpleName, "***** Child save invoked: ")
        super.save(input)

    }
}
