package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainModel
import io.reactivex.Observable

interface ImageUseCasesContract {

    interface GetLatestImages{
        fun getLatestImages(): Observable<ResultDomainModel>
    }

    interface SearchImages{
        fun searchImages(searchQuery: String): Observable<ResultDomainModel>
    }
}
