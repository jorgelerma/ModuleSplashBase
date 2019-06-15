package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.service.api.SplashbaseApi
import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImagesServicese
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class ImageServiceImpl(private val mapper: ImageMapper): ImagesServicese {

    private val URL = "http://splashbase.co/"

    override fun getLatestImagesRx(): Observable<ResultDomainInput> {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(SplashbaseApi::class.java).getTheLatestImages()
                .map { resp -> mapper.mapDataModelToDomainModel(resp) }
    }

    override fun getLatestImages() {
        //TODO
    }
}
