package com.globant.equattrocchio.data.service.api

import com.globant.equattrocchio.data.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ImagesApiImpl @Inject constructor() : ImagesApi {

    private lateinit var retrofitInstance: SplashbaseApi

    override fun getInstance(): SplashbaseApi {

        if(!::retrofitInstance.isInitialized){
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            retrofitInstance = retrofit.create(SplashbaseApi::class.java)
        }
        return retrofitInstance
    }
}
