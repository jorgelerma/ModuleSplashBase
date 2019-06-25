package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.Observable

interface ImageUseCasesContract {

    interface GetLatestImagesContract{
        fun getLatestImages(): Observable<ResultDomainInput>
    }

    interface SearchImagesContract{
        fun searchImage(searchQuery: String): Observable<ResultDomainInput>
    }
}