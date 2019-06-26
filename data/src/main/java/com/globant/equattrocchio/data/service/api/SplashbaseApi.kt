package com.globant.equattrocchio.data.service.api

import com.globant.equattrocchio.data.response.ResultDataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SplashbaseApi {

    @GET("api/v1/images/latest")
    fun getLatestImages(): Observable<ResultDataModel>

    @GET("api/v1/images/search")
    fun searchQueryImage(@Query("query") imageQuery: String): Observable<ResultDataModel>
}
