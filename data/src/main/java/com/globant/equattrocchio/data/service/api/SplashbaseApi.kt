package com.globant.equattrocchio.data.service.api

import com.globant.equattrocchio.data.response.ResultInput
import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface SplashbaseApi {

    @GET("api/v1/images/latest")
    fun getImages() : Call<ResultInput>

    @GET("api/v1/images/latest")
    fun getTheLatestImages() : Observable<ResultInput>
}
