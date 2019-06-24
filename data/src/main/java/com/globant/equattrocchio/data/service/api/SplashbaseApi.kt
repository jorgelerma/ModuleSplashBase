package com.globant.equattrocchio.data.service.api

import com.globant.equattrocchio.data.response.ResultDataInput
import io.reactivex.Observable
import retrofit2.http.GET

interface SplashbaseApi {

    @GET("api/v1/images/latest")
    fun getLatestImages() : Observable<ResultDataInput>
}
