package com.globant.equattrocchio.domain.service

import com.globant.equattrocchio.domain.models.ResultDomainModel
import io.reactivex.Observable

interface ImageService {

    fun getLatestImages(): Observable<ResultDomainModel>

    fun searchImages(searchQuery: String): Observable<ResultDomainModel>
}
