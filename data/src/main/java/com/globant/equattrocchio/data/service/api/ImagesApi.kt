package com.globant.equattrocchio.data.service.api

import android.util.Log
import com.globant.equattrocchio.data.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ImagesApi {

    private lateinit var retrofitInstance: SplashbaseApi

    fun getInstance(): SplashbaseApi {

        if(!::retrofitInstance.isInitialized){
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            Log.e(this.javaClass.simpleName, " getInstance invoked: ")
            retrofitInstance = retrofit.create(SplashbaseApi::class.java)
        }
        Log.e(this.javaClass.simpleName, " @ImagesApi: ")
        return retrofitInstance
    }
}
