package com.globant.equattrocchio.data.service.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ImagesApi {

    private lateinit var retrofitInstance: SplashbaseApi
    private val URL = "http://splashbase.co/"

    fun getInstance(): SplashbaseApi {

        if(!::retrofitInstance.isInitialized){
            val retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            retrofitInstance = retrofit.create(SplashbaseApi::class.java)
        }
        return retrofitInstance
    }
}
